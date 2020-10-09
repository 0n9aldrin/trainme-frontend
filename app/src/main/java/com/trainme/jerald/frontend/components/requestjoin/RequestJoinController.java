package com.trainme.jerald.frontend.components.requestjoin;

import android.support.annotation.NonNull;

import com.trainme.jerald.frontend.dependencies.component.AppComponent;
import com.trainme.jerald.frontend.dependencies.models.RequestJoinSparing;
import com.trainme.jerald.frontend.dependencies.webservices.RequestJoinService;

import javax.inject.Inject;

public class RequestJoinController implements RequestJoinContract.Controller {

    @Inject
    RequestJoinService mService;

    private RequestJoinContract.View views;

    public RequestJoinController(@NonNull AppComponent appComponent) {
        appComponent.inject(this);
    }

    public void setView(RequestJoinContract.View view) {
        views = view;
    }

    @Override
    public void requestJoin(RequestJoinSparing requestModel) {
        mService.instanceClass(this);
        mService.requestJoin(requestModel);
    }

    @Override
    public void requestJoinSuccess(String message) {
        views.requestJoinSuccess(message);
    }

    @Override
    public void requestJoinFailed(String message) {
        views.requestJoinFailed(message);
    }
}
