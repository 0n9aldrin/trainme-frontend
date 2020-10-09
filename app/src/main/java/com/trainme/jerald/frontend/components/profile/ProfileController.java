package com.trainme.jerald.frontend.components.profile;

import android.support.annotation.NonNull;

import com.trainme.jerald.frontend.dependencies.component.AppComponent;
import com.trainme.jerald.frontend.dependencies.models.Profile;
import com.trainme.jerald.frontend.dependencies.response.model.User;
import com.trainme.jerald.frontend.dependencies.webservices.ProfileService;

import javax.inject.Inject;

import io.realm.Realm;

public class ProfileController implements ProfileContract.Controller {

    @Inject
    Realm realm;

    @Inject
    ProfileService mService;

    private ProfileContract.View views;

    public ProfileController(@NonNull AppComponent appComponent) {
        appComponent.inject(this);
    }

    public void setView(ProfileContract.View view) {
        views = view;
    }

    public int getIdUser() {
        realm.beginTransaction();
        User user = realm.where(User.class).findFirst();
        realm.commitTransaction();
        if (user == null) {
            return 0;
        } else {
            return user.getId();
        }
    }

    @Override
    public void getData() {
        mService.instanceClass(this);
        mService.getData(getIdUser());
    }

    @Override
    public void getDataSuccess(Profile data) {
        views.getDataSuccess(data);
    }

    @Override
    public void getDataFailed(String message) {
        views.getDataFailed(message);
    }
}
