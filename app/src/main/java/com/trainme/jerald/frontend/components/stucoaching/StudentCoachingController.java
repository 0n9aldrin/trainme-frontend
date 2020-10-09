package com.trainme.jerald.frontend.components.stucoaching;

import android.support.annotation.NonNull;

import com.trainme.jerald.frontend.dependencies.component.AppComponent;
import com.trainme.jerald.frontend.dependencies.response.model.SparringInvitation;
import com.trainme.jerald.frontend.dependencies.response.model.User;
import com.trainme.jerald.frontend.dependencies.webservices.StudentCoachingService;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;

public class StudentCoachingController implements StudentCoachingContract.Controller {

    @Inject
    StudentCoachingService mService;

    @Inject
    Realm realm;

    private StudentCoachingContract.View views;

    public StudentCoachingController(@NonNull AppComponent appComponent) {
        appComponent.inject(this);
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

    public void setView(StudentCoachingContract.View view) {
        views = view;
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

