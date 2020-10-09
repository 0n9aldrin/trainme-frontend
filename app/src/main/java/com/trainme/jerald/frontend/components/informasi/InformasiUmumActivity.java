package com.trainme.jerald.frontend.components.informasi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

import com.joanzapata.iconify.widget.IconTextView;
import com.trainme.jerald.frontend.MainApplication;
import com.trainme.jerald.frontend.R;
import com.trainme.jerald.frontend.components.adapters.InformasiUmumAdapter;
import com.trainme.jerald.frontend.components.base.BaseActivity;
import com.trainme.jerald.frontend.dependencies.models.InformasiUmum;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InformasiUmumActivity  extends BaseActivity implements InformasiUmumContract.View  {

    @Inject
    InformasiUmumController mController;

    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.spinnerIcon)
    IconTextView spinner;
    @BindView(R.id.recyclerViewNews)
    RecyclerView recyclerView;

    @BindView(R.id.activity_news)
    RelativeLayout relativeLayout;

    List<InformasiUmum> listData = Collections.EMPTY_LIST;

    private final String LAYOUT_ERROR = "Error";
    private final String LAYOUT_LOADING = "Loading";
    private final String LAYOUT_SUCCESS = "Success";
    private final String LAYOUT_EMPTY = "No News :( ";
    private static String ACTION_TITLE_REFRESH = "REFRESH";

    RecyclerView.LayoutManager linearLayoutManager;
    public InformasiUmumAdapter mAdapter;
    public static Intent createIntent(Context context) {
        return new Intent(context, InformasiUmumActivity.class);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informasi_umum);

        ((MainApplication) getApplication())
                .getComponent()
                .inject(this);
        ButterKnife.bind(this);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        updateLayout(LAYOUT_LOADING);
        mController.setView(this);
        mController.getData();
    }
    private void updateLayout(String status) {
        switch (status) {
            case LAYOUT_SUCCESS:
                spinner.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                break;
            case LAYOUT_EMPTY:
                spinner.setText("Mohon Maaf, Data Kosong");
                break;
            case LAYOUT_ERROR:
                createSnackbar(LAYOUT_ERROR).show();
                spinner.setText("{fa-info 200%} Error");
                break;
            case LAYOUT_LOADING:
                recyclerView.setVisibility(View.GONE);
                spinner.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }
    @Override
    public void getDataSuccess(List<InformasiUmum> data) {
        listData = data;
        mAdapter = new InformasiUmumAdapter(getApplicationContext(), listData);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        updateLayout(LAYOUT_SUCCESS);
    }

    @Override
    public void getDataFailed(String message) {
        updateLayout(LAYOUT_EMPTY);
        onError(message);
    }
    private Snackbar createSnackbar(String message) {
        return Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_INDEFINITE)
                .setAction(ACTION_TITLE_REFRESH, v -> mController.getData());
    }
}
