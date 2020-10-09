package com.trainme.jerald.frontend.components.requestersparring;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.joanzapata.iconify.widget.IconTextView;
import com.trainme.jerald.frontend.MainApplication;
import com.trainme.jerald.frontend.R;
import com.trainme.jerald.frontend.components.adapters.RequesterSparringAdapter;
import com.trainme.jerald.frontend.components.approval.ApprovalSparringFragment;
import com.trainme.jerald.frontend.components.base.BaseActivity;
import com.trainme.jerald.frontend.dependencies.response.model.RequesterSparring;
import com.trainme.jerald.frontend.utils.AppConstants;
import com.trainme.jerald.frontend.utils.Message;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RequesterSparringActivity extends BaseActivity implements RequesterSparringContract.View
        , RequesterSparringAdapter.OnClickListener {

    @Inject
    RequesterSparringController mController;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.spinnerIcon)
    IconTextView spinner;
    RequesterSparringAdapter mAdapter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.activity)
    RelativeLayout relativeLayout;
    RecyclerView.LayoutManager linearLayoutManager;

    private static int playId;

    public static Intent createIntent(Context context, int idPlay) {
        playId = idPlay;
        return new Intent(context, RequesterSparringActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requester_sparring);
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
    public void getDataSuccess(List<RequesterSparring> data) {
        mAdapter = new RequesterSparringAdapter(getApplicationContext(), data, this);
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

    @Override
    public void OnClickItem(RequesterSparring data) {
        if (data.getStatus().compareTo("waiting") == 0) {
            ApprovalSparringFragment.newInstance(data.getPpId()).show(getFragmentManager(), "");
        }
    }
}
