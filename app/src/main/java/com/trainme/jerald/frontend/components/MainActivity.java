package com.trainme.jerald.frontend.components;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.afollestad.materialdialogs.MaterialDialog;
import com.trainme.jerald.frontend.MainApplication;
import com.trainme.jerald.frontend.R;
import com.trainme.jerald.frontend.components.about.AboutActivity;
import com.trainme.jerald.frontend.components.adapters.ImageSlideAdapter;
import com.trainme.jerald.frontend.components.base.BaseActivity;
import com.trainme.jerald.frontend.components.events.EventActivity;
import com.trainme.jerald.frontend.components.historycoaching.HistoryCoachingActivity;
import com.trainme.jerald.frontend.components.home.HomeContract;
import com.trainme.jerald.frontend.components.home.HomeController;
import com.trainme.jerald.frontend.components.profile.ProfileActivity;
import com.trainme.jerald.frontend.components.ranking.RankingManActivity;
import com.trainme.jerald.frontend.components.ranking.RankingWomanActivity;
import com.trainme.jerald.frontend.components.signin.SigninActivity;
import com.trainme.jerald.frontend.components.slide.CheckNetworkConnection;
import com.trainme.jerald.frontend.components.slide.CirclePageIndicator;
import com.trainme.jerald.frontend.components.slide.PageIndicator;
import com.trainme.jerald.frontend.components.sparring.SparringActivity;
import com.trainme.jerald.frontend.components.stucoaching.StudentCoachingActivity;
import com.trainme.jerald.frontend.dependencies.response.model.AdsModel;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements HomeContract.View {

    @Inject
    HomeController mController;
    @BindView(R.id.frghome)
    LinearLayout layout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.sparring)
    Button sparring;
    @OnClick(R.id.sparring)
    void click() {
        startActivity(SparringActivity.createIntent(getApplicationContext()));
    }

    @BindView(R.id.rankingMan)
    Button rankingMan;
    @OnClick(R.id.rankingMan)
    void clickRankingMan() {
        startActivity(RankingManActivity.createIntent(getApplicationContext()));
    }

    @BindView(R.id.rankingWoman)
    Button rankingWoman;
    @OnClick(R.id.rankingWoman)
    void clickRankingWoman() {
        startActivity(RankingWomanActivity.createIntent(getApplicationContext()));
    }

    @BindView(R.id.courseCoach)
    Button courseCoach;
    @OnClick(R.id.courseCoach)
    void clickCourseCoach(){
        if(mController.getIdRole() == 2){
            startActivity(StudentCoachingActivity.createIntent(getApplicationContext()));    
        }else{
            onError("This menu only for Coach");
        }
        
    }

    @BindView(R.id.historyInvite)
    Button historyInvite;
    @OnClick(R.id.historyInvite)
    void clickHisotryInviteCoach(){
        startActivity(HistoryCoachingActivity.createIntent(getApplicationContext()));
    }

    @BindView(R.id.event)
    Button event;
    @OnClick(R.id.event)
    void clickEvent(){
        startActivity(EventActivity.createIntent(getApplicationContext()));
    }

    //Slider
    private static final long ANIM_VIEWPAGER_DELAY = 5000;
    private static final long ANIM_VIEWPAGER_DELAY_USER_VIEW = 10000;
    AlertDialog alertDialog;
    PageIndicator mIndicator;
    private Runnable animateViewPager;
    private Handler handler;
    boolean stopSliding = false;
    Activity activity;
    List<AdsModel> products;
    RequestImgTask task;
    String message;
    ViewPager mViewPager;

    public static Intent createIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((MainApplication) getApplication())
                .getComponent()
                .inject(this);
        ButterKnife.bind(this);
        getDelegate().setSupportActionBar(toolbar);
        mIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        activity = this;
        mController.setView(this);
        mController.getAds();
    }

    @Override
    public void getAdsSuccess(List<AdsModel> data) {
        Log.e("Incall", "getads");
        products = new ArrayList<AdsModel>();
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

    }

    @Override
    public void getAdsFailed(String message) {
        showErrorMessage(message);
    }

    @Override
    public void signOutSuccess() {
        hideLoading();
        finish();
        startActivity(SigninActivity.createIntent(getApplicationContext()));
    }

    private Snackbar showErrorMessage(String message) {
        return Snackbar.make(layout, "Something Wrong " + message, Snackbar.LENGTH_INDEFINITE);
    }


    private class RequestImgTask extends AsyncTask<String, Void, List<AdsModel>> {
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
        protected List<AdsModel> doInBackground(String... urls) {
            return products;
        }


        @Override
        protected void onPostExecute(List<AdsModel> result) {
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

                            mViewPager.setAdapter(new ImageSlideAdapter(getApplicationContext(), products));

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
        Log.e("In call", "Resume");
        Log.e("Products", ""+ products.size());
        if (products == null) {
            sendRequest();
        } else {
            mViewPager.setAdapter(new ImageSlideAdapter(this, products));

            mIndicator.setViewPager(mViewPager);
            runnable(products.size());
            handler.postDelayed(animateViewPager, ANIM_VIEWPAGER_DELAY);
        }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navigation, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_signout:
                logoutDialog();
                return true;
            case R.id.action_about:
                startActivity(AboutActivity.createIntent(getApplicationContext()));
                return true;
            case R.id.action_profile:
                startActivity(ProfileActivity.createIntent(getApplicationContext()));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void logoutDialog() {
        MaterialDialog dialog = new MaterialDialog.Builder(this)
                .title(R.string.title_logout)
                .content(R.string.content_logout)
                .positiveText(R.string.yes)
                .negativeText(R.string.no)
                .onPositive((dialog1, which) -> {
                    showLoading();
                    mController.signOut();
                })
                .onNegative((dialog1, which) -> {

                })
                .build();
        dialog.show();
    }

}
