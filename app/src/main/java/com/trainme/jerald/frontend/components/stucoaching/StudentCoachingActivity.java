package com.trainme.jerald.frontend.components.stucoaching;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.joanzapata.iconify.widget.IconTextView;
import com.trainme.jerald.frontend.MainApplication;
import com.trainme.jerald.frontend.R;
import com.trainme.jerald.frontend.components.adapters.StudentCoachingAdapter;
import com.trainme.jerald.frontend.components.base.BaseActivity;
import com.trainme.jerald.frontend.components.myrequestcoachingstatus.MyRequestCoachingStatusActivity;
import com.trainme.jerald.frontend.components.requestjoincoach.RequestJoinCoachFragment;
import com.trainme.jerald.frontend.dependencies.response.model.SparringInvitation;
import com.trainme.jerald.frontend.utils.AppConstants;
import com.trainme.jerald.frontend.utils.Message;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StudentCoachingActivity extends BaseActivity implements StudentCoachingContract.View,
        StudentCoachingAdapter.OnClickListener {

    @Inject
    StudentCoachingController mController;
    @BindView(R.id.recyclerViewProduct)
    RecyclerView recyclerView;
    @BindView(R.id.spinnerIcon)
    IconTextView spinner;
    StudentCoachingAdapter mAdapter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.activity)
    RelativeLayout relativeLayout;
    RecyclerView.LayoutManager linearLayoutManager;

    public static Intent createIntent(Context context) {
        return new Intent(context, StudentCoachingActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_coaching);
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
        mAdapter = new StudentCoachingAdapter(getApplicationContext(),data, this);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        updateLayout(AppConstants.LAYOUT_SUCCESS);
    }

    @Override
    public void getDataFailed(String message) {
        onError(message);
        updateLayout(AppConstants.LAYOUT_ERROR);
    }

    @Override
    public void OnClickItem(SparringInvitation data) {
        RequestJoinCoachFragment.newInstance(data.getPlayId(), mController.getIdUser()).show(getFragmentManager(), "");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_coaching, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_status:
                startActivity(MyRequestCoachingStatusActivity.createIntent(getApplicationContext()));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
