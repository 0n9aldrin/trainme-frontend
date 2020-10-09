package com.trainme.jerald.frontend.components.ranking;

import android.support.annotation.NonNull;

import com.trainme.jerald.frontend.dependencies.component.AppComponent;
import com.trainme.jerald.frontend.dependencies.models.Ranking;
import com.trainme.jerald.frontend.dependencies.webservices.RankingManService;
import com.trainme.jerald.frontend.dependencies.webservices.RankingWomanService;

import java.util.List;

import javax.inject.Inject;

public class RankingWomanController implements RankingContract.Controller {

    @Inject
    RankingWomanService mService;

    private RankingContract.View views;

    public RankingWomanController(@NonNull AppComponent appComponent) {
        appComponent.inject(this);
    }

    public void setView(RankingContract.View view) {
        views = view;
    }

    @Override
    public void getData() {
        mService.instanceClass(this);
        mService.getData();
    }

    @Override
    public void getDataSuccess(List<Ranking> data) {
        views.getDataSuccess(data);
    }

    @Override
    public void getDataFailed(String message) {
        views.getDataFailed(message);
    }
}
