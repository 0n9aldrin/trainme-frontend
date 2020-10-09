package com.trainme.jerald.frontend.components.historycoaching;

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
import com.trainme.jerald.frontend.components.adapters.HistoryCoachingAdapter;
import com.trainme.jerald.frontend.components.addcoaching.AddCoachingActivity;
import com.trainme.jerald.frontend.components.base.BaseActivity;
import com.trainme.jerald.frontend.components.requestercoaching.RequesterCoachingActivity;
import com.trainme.jerald.frontend.dependencies.response.model.SparringInvitation;
import com.trainme.jerald.frontend.utils.AppConstants;
import com.trainme.jerald.frontend.utils.Message;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HistoryCoachingActivity extends BaseActivity implements HistoryCoachingContract.View,
        HistoryCoachingAdapter.OnClickListener  {

    @Inject
    HistoryCoachingController mController;
    @BindView(R.id.recyclerViewProduct)
    RecyclerView recyclerView;
    @BindView(R.id.spinnerIcon)
    IconTextView spinner;
    HistoryCoachingAdapter mAdapter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.activity)
    RelativeLayout relativeLayout;

    @BindView(R.id.addCoaching)
    FloatingActionButton addCoaching;
    @OnClick(R.id.addCoaching)
    void clickInviteCoach(){
        startActivity(AddCoachingActivity.createIntent(getApplicationContext()));
    }
    RecyclerView.LayoutManager linearLayoutManager;

    public static Intent createIntent(Context context) {
        return new Intent(context, HistoryCoachingActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_coaching);
        ((MainApplication) getApplication())
                .getComponent()
                .inject(this);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getDelegate().setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        updateLayout(AppConstants.LAYOUT_LOADING);
        mController.setView(this);
        mController.getData();
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
    public void getDataSuccess(List<SparringInvitation> data) {
        mAdapter = new HistoryCoachingAdapter(getApplicationContext(),data, this);
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
    public void OnClickItem(SparringInvitation data) {
        startActivity(RequesterCoachingActivity.createIntent(getApplicationContext(), data.getPlayId()));
    }

}
