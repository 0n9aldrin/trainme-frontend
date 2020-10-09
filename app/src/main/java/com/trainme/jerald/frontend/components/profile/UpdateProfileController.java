package com.trainme.jerald.frontend.components.profile;

import android.support.annotation.NonNull;

import com.trainme.jerald.frontend.dependencies.component.AppComponent;
import com.trainme.jerald.frontend.dependencies.webservices.UpdateProfileService;

import javax.inject.Inject;

import okhttp3.RequestBody;

public class UpdateProfileController implements UpdateProfileContract.Controller {

    @Inject
    UpdateProfileService mService;

    private UpdateProfileContract.View views;

    public UpdateProfileController(@NonNull AppComponent appComponent) {
        appComponent.inject(this);
    }

    public void setView(UpdateProfileContract.View view) {
        views = view;
    }

    @Override
    public void updateData(RequestBody data, RequestBody idUser) {
        mService.instanceClass(this);
        mService.updateData(data, idUser);
    }

    @Override
    public void updateDataSuccess() {
        views.updateDataSuccess();
    }

    @Override
    public void updateDataFailed(String message) {
        views.updateDataFailed(message);
    }
}
