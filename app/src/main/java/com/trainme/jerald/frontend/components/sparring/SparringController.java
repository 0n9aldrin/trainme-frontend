package com.trainme.jerald.frontend.components.sparring;

import android.support.annotation.NonNull;

import com.trainme.jerald.frontend.dependencies.component.AppComponent;
import com.trainme.jerald.frontend.dependencies.response.model.SparringInvitation;
import com.trainme.jerald.frontend.dependencies.response.model.User;
import com.trainme.jerald.frontend.dependencies.webservices.SparringService;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;

public class SparringController implements SparringContract.Controller {

    @Inject
    SparringService mService;

    @Inject
    Realm realm;

    private SparringContract.View views;

    public SparringController(@NonNull AppComponent appComponent) {
        appComponent.inject(this);
    }

    public void setView(SparringContract.View view) {
        views = view;
    }

    public int getIdUser(){
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
        mService.getData();
    }

    @Override
    public void getDataSuccess(List<SparringInvitation> data) {
        views.getDataSuccess(data);
    }

    @Override
    public void getDataFailed(String message) {
        views.getDataFailed(message);
    }
}
