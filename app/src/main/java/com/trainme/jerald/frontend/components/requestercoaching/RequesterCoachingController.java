package com.trainme.jerald.frontend.components.requestercoaching;

import android.support.annotation.NonNull;
import android.util.Log;

import com.trainme.jerald.frontend.dependencies.component.AppComponent;
import com.trainme.jerald.frontend.dependencies.response.model.RequesterSparring;
import com.trainme.jerald.frontend.dependencies.webservices.RequesterCoachingService;
import java.util.List;
import javax.inject.Inject;

public class RequesterCoachingController implements RequesterCoachingContract.Controller {

    @Inject
    RequesterCoachingService mService;

    private RequesterCoachingContract.View views;

    public RequesterCoachingController(@NonNull AppComponent appComponent) {
        appComponent.inject(this);
    }

    public void setView(RequesterCoachingContract.View view) {
        views = view;
    }

    @Override
    public void getData(int id) {
        mService.instanceClass(this);
        mService.getData(id);
    }

    @Override
    public void getDataSuccess(List<RequesterSparring> data) {
        views.getDataSuccess(data);
    }

    @Override
    public void getDataFailed(String message) {
        views.getDataFailed(message);
    }
}

