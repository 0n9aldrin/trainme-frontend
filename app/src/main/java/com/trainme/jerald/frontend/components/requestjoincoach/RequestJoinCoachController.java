package com.trainme.jerald.frontend.components.requestjoincoach;

import android.support.annotation.NonNull;
import com.trainme.jerald.frontend.dependencies.component.AppComponent;
import com.trainme.jerald.frontend.dependencies.models.RequestJoinSparing;
import com.trainme.jerald.frontend.dependencies.webservices.RequestJoinCoachService;

import javax.inject.Inject;

public class RequestJoinCoachController implements RequestJoinCoachContract.Controller {

    @Inject
    RequestJoinCoachService mService;

    private RequestJoinCoachContract.View views;

    public RequestJoinCoachController(@NonNull AppComponent appComponent) {
        appComponent.inject(this);
    }

    public void setView(RequestJoinCoachContract.View view) {
        views = view;
    }

    @Override
    public void requestJoinCoach(RequestJoinSparing requestModel) {
        mService.instanceClass(this);
        mService.requestJoinCoach(requestModel);
    }

    @Override
    public void requestJoinCoachSuccess(String message) {
        views.requestJoinCoachSuccess(message);
    }

    @Override
    public void requestJoinCoachFailed(String message) {
        views.requestJoinCoachFailed(message);
    }
}
