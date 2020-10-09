package com.trainme.jerald.frontend.components.ranking;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import com.trainme.jerald.frontend.components.adapters.RankingWomanAdapter;
import com.trainme.jerald.frontend.components.base.BaseActivity;
import com.trainme.jerald.frontend.dependencies.models.Ranking;
import com.trainme.jerald.frontend.utils.AppConstants;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RankingWomanActivity  extends BaseActivity implements RankingContract.View,
        RankingWomanAdapter.OnClickListener  {

    @Inject
    RankingWomanController mController;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.spinnerIcon)
    IconTextView spinner;
    RankingWomanAdapter mAdapter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.activity)
    RelativeLayout relativeLayout;

    RecyclerView.LayoutManager linearLayoutManager;

    public static Intent createIntent(Context context) {
        return new Intent(context, RankingWomanActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking_woman);
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
    public void getDataSuccess(List<Ranking> data) {
        mAdapter = new RankingWomanAdapter(getApplicationContext(),data, this);
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
    public void OnClickItem(Ranking data) {
        Intent viewIntent =
                new Intent("android.intent.action.VIEW",
                        Uri.parse(data.getLink()));
        startActivity(viewIntent);
    }
}
