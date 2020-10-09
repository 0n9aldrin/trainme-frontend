package com.trainme.jerald.frontend.components.requestersparring;

import android.support.annotation.NonNull;

import com.trainme.jerald.frontend.dependencies.component.AppComponent;
import com.trainme.jerald.frontend.dependencies.response.model.RequesterSparring;
import com.trainme.jerald.frontend.dependencies.response.model.User;
import com.trainme.jerald.frontend.dependencies.webservices.RequesterSparringService;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;

public class RequesterSparringController implements RequesterSparringContract.Controller {

    @Inject
    RequesterSparringService mService;

    private RequesterSparringContract.View views;

    public RequesterSparringController(@NonNull AppComponent appComponent) {
        appComponent.inject(this);
    }

    public void setView(RequesterSparringContract.View view) {
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

