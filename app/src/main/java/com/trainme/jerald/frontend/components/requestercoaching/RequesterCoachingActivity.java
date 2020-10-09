package com.trainme.jerald.frontend.components.requestercoaching;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

import com.joanzapata.iconify.widget.IconTextView;
import com.trainme.jerald.frontend.MainApplication;
import com.trainme.jerald.frontend.R;
import com.trainme.jerald.frontend.components.adapters.RequesterCoachingAdapter;
import com.trainme.jerald.frontend.components.approvalcoaching.ApprovalCoachingFragment;
import com.trainme.jerald.frontend.components.base.BaseActivity;
import com.trainme.jerald.frontend.components.requestersparring.RequesterSparringController;
import com.trainme.jerald.frontend.components.showimage.PreviewActivity;
import com.trainme.jerald.frontend.dependencies.response.model.RequesterSparring;
import com.trainme.jerald.frontend.utils.AppConstants;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RequesterCoachingActivity extends BaseActivity implements RequesterCoachingContract.View
        , RequesterCoachingAdapter.OnClickListener {

    @Inject
    RequesterCoachingController mController;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.spinnerIcon)
    IconTextView spinner;
    RequesterCoachingAdapter mAdapter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.activity)
    RelativeLayout relativeLayout;
    RecyclerView.LayoutManager linearLayoutManager;

    private static int playId;

    public static Intent createIntent(Context context, int idPlay) {
        playId = idPlay;
        return new Intent(context, RequesterCoachingActivity.class);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requester_coaching);
        ((MainApplication) getApplication())
                .getComponent()
                .inject(this);
        ButterKnife.bind(this);
        updateLayout(AppConstants.LAYOUT_LOADING);
        mController.setView(this);
        mController.getData(playId);
    }

    private void updateLayout(String status) {
        switch (status) {
            case AppConstants.LAYOUT_SUCCESS:
                spinner.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                break;
            case AppConstants.LAYOUT_EMPTY:
                spinner.setText("{fa-info 200%}  No data found");
                break;
            case AppConstants.LAYOUT_ERROR:
                spinner.setText("{fa-info 200%} Error");
                break;
            case AppConstants.LAYOUT_LOADING:
                recyclerView.setVisibility(View.GONE);
                spinner.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }

    @Override
    public void OnClickItem(RequesterSparring data) {
        if (data.getStatus().compareTo("waiting") == 0) {
            ApprovalCoachingFragment.newInstance(data.getPpId()).show(getFragmentManager(), "");
        }
    }

    @Override
    public void showImage(String url) {
        startActivity(PreviewActivity.createIntent(getApplicationContext(), url));
    }

    @Override
    public void getDataSuccess(List<RequesterSparring> data) {
        mAdapter = new RequesterCoachingAdapter(getApplicationContext(), data, this);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        updateLayout(AppConstants.LAYOUT_SUCCESS);
    }

    @Override
    public void getDataFailed(String message) {
        onError(message);
        updateLayout(AppConstants.LAYOUT_EMPTY);
    }
}
