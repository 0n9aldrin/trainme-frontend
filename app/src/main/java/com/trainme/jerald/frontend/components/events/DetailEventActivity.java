package com.trainme.jerald.frontend.components.events;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.joanzapata.iconify.widget.IconTextView;
import com.trainme.jerald.frontend.MainApplication;
import com.trainme.jerald.frontend.R;
import com.trainme.jerald.frontend.components.adapters.EventPhotoAdapter;
import com.trainme.jerald.frontend.components.base.BaseActivity;
import com.trainme.jerald.frontend.components.eventphoto.EventPhotoContract;
import com.trainme.jerald.frontend.components.eventphoto.EventPhotoController;
import com.trainme.jerald.frontend.components.slide.CheckNetworkConnection;
import com.trainme.jerald.frontend.components.slide.CirclePageIndicator;
import com.trainme.jerald.frontend.components.slide.PageIndicator;
import com.trainme.jerald.frontend.dependencies.models.AllEvents;
import com.trainme.jerald.frontend.dependencies.models.EventPhoto;
import com.trainme.jerald.frontend.utils.AppConstants;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailEventActivity extends BaseActivity implements EventPhotoContract.View {

    @Inject
    EventPhotoController mController;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.imageSlideShow)
    RelativeLayout imageSlideShow;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.description)
    TextView description;
    @BindView(R.id.startEvent)
    TextView startEvent;
    @BindView(R.id.endEvent)
    TextView endEvent;
    @BindView(R.id.linkEvent)
    TextView linkEvent;
    @BindView(R.id.spinnerIcon)
    IconTextView spinner;

    private static final long ANIM_VIEWPAGER_DELAY = 5000;
    private static final long ANIM_VIEWPAGER_DELAY_USER_VIEW = 10000;
    AlertDialog alertDialog;
    PageIndicator mIndicator;
    private Runnable animateViewPager;
    private Handler handler;
    boolean stopSliding = false;
    Activity activity;
    List<EventPhoto> products;
    RequestImgTask task;
    String message;
    ViewPager mViewPager;

    private static AllEvents events;

    public static Intent createIntent(Context context, AllEvents ev) {
        events = ev;
        Intent intent = new Intent(context, DetailEventActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_event);
        ((MainApplication) getApplication())
                .getComponent()
                .inject(this);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getDelegate().setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        updateLayout(AppConstants.LAYOUT_LOADING);
        activity = this;
        mController.setView(this);
        mController.getData(events.getId());
        setContent();
    }

    private void updateLayout(String status) {
        switch (status) {
            case AppConstants.LAYOUT_SUCCESS:
                spinner.setVisibility(View.GONE);
                imageSlideShow.setVisibility(View.VISIBLE);
                break;
            case AppConstants.LAYOUT_EMPTY:
                spinner.setText("{fa-info 200%}  No data found");
                break;
            case AppConstants.LAYOUT_ERROR:
                spinner.setText("{fa-info 200%} Error");
                break;
            case AppConstants.LAYOUT_LOADING:
                imageSlideShow.setVisibility(View.GONE);
                spinner.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }

    private void setContent() {
        description.setText(events.getDescription());
        title.setText(events.getTitle());
        String[] startDate =  events.getStartDate().split(" ");
        String[] endDate =  events.getEndDate().split(" ");
        startEvent.setText("Event Start: " + startDate[0]);
        endEvent.setText("Event End  : " + endDate[0]);
        linkEvent.setOnClickListener(v -> {
            Intent viewIntent =
                    new Intent("android.intent.action.VIEW",
                            Uri.parse(events.getLink()));
            startActivity(viewIntent);
        });
    }

    @Override
    public void getDataSuccess(List<EventPhoto> data) {
        Log.e("Success","Data exist");
        showImage(data);

        updateLayout(AppConstants.LAYOUT_SUCCESS);
    }

    @Override
    public void getDataFailed(String message) {
        Log.e("Failed", ""+message);
        showImage(new ArrayList<>());
        updateLayout(AppConstants.LAYOUT_SUCCESS);
    }

    private void showImage(List<EventPhoto> data) {
        updateLayout(AppConstants.LAYOUT_SUCCESS);
        products = new ArrayList<>();
        products.add(new EventPhoto(0, events.getId(), events.getPoster(), events.getDescription()));
        for (int a = 0; a < data.size(); a++) {
            products.add(data.get(a));
        }
        mIndicator.setOnPageChangeListener(new PageChangeListener());
        mViewPager.setOnPageChangeListener(new PageChangeListener());
        mViewPager.setOnTouchListener((v, event) -> {
            v.getParent().requestDisallowInterceptTouchEvent(true);
            switch (event.getAction()) {

                case MotionEvent.ACTION_CANCEL:
                    break;

                case MotionEvent.ACTION_UP:
                    // calls when touch release on ViewPager
                    if (products != null && products.size() != 0) {
                        stopSliding = false;
                        runnable(products.size());
                        handler.postDelayed(animateViewPager,
                                ANIM_VIEWPAGER_DELAY_USER_VIEW);
                    }
                    break;

                case MotionEvent.ACTION_MOVE:
                    // calls when ViewPager touch
                    if (handler != null && stopSliding == false) {
                        stopSliding = true;
                        handler.removeCallbacks(animateViewPager);
                    }
                    break;
            }
            return false;
        });

        if (products == null) {
            sendRequest();
        } else {
            mViewPager.setAdapter(new EventPhotoAdapter(this, products));
            mIndicator.setViewPager(mViewPager);
            runnable(products.size());
            handler.postDelayed(animateViewPager, ANIM_VIEWPAGER_DELAY);
        }
    }

    private class RequestImgTask extends AsyncTask<String, Void, List<EventPhoto>> {
        private final WeakReference<Activity> activityWeakRef;
        Throwable error;

        public RequestImgTask(Activity context) {
            this.activityWeakRef = new WeakReference<Activity>(context);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<EventPhoto> doInBackground(String... urls) {
            return products;
        }


        @Override
        protected void onPostExecute(List<EventPhoto> result) {
            super.onPostExecute(result);

            if (activityWeakRef != null && !activityWeakRef.get().isFinishing()) {
                if (error != null && error instanceof IOException) {
                    message = getResources().getString(R.string.time_out);
                    showAlertDialog(message, true);
                } else if (error != null) {
                    message = getResources().getString(R.string.error_occured);
                    showAlertDialog(message, true);
                } else {
                    products = result;
                    if (result != null) {
                        if (products != null && products.size() != 0) {

                            mViewPager.setAdapter(new EventPhotoAdapter(getApplicationContext(), products));

                            mIndicator.setViewPager(mViewPager);
                            runnable(products.size());
                            handler.postDelayed(animateViewPager,
                                    ANIM_VIEWPAGER_DELAY);
                        } else {
                            //imgNameTxt.setText("No Products");
                        }
                    } else {
                    }
                }
            }
        }
    }

    public void showAlertDialog(String message, final boolean finish) {
        alertDialog = new AlertDialog.Builder(activity).create();
        alertDialog.setMessage(message);
        alertDialog.setCancelable(false);

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                (dialog, which) -> {
                    dialog.dismiss();
                    if (finish)
                        activity.finish();
                });
        alertDialog.show();
    }
    private class PageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == ViewPager.SCROLL_STATE_IDLE) {
                if (products != null) {

                }
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int arg0) {
        }
    }

    public void runnable(final int size) {
        handler = new Handler();
        animateViewPager = () -> {
            if (!stopSliding) {
                if (mViewPager.getCurrentItem() == size - 1) {
                    mViewPager.setCurrentItem(0);
                } else {
                    mViewPager.setCurrentItem(
                            mViewPager.getCurrentItem() + 1, true);
                }
                handler.postDelayed(animateViewPager, ANIM_VIEWPAGER_DELAY);
            }
        };
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void sendRequest() {
        if (CheckNetworkConnection.isConnectionAvailable(activity)) {
            task = new RequestImgTask(activity);
            task.execute();
        } else {
            //message = getResources().getString(R.string.no_internet_connection);
            showAlertDialog("No internet connection", true);
        }
    }

    @Override
    public void onPause() {
        if (task != null)
            task.cancel(true);
        if (handler != null) {
            //Remove callback
            handler.removeCallbacks(animateViewPager);
        }
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
