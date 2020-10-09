package com.trainme.jerald.frontend.components.myrequestsparringstatus;

import android.support.annotation.NonNull;

import com.trainme.jerald.frontend.dependencies.component.AppComponent;
import com.trainme.jerald.frontend.dependencies.response.model.MyRequestSparringStatus;
import com.trainme.jerald.frontend.dependencies.response.model.User;
import com.trainme.jerald.frontend.dependencies.webservices.MyRequestSparringStatusService;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;

public class MyRequestSparringStatusController implements MyRequestSparringStatusContract.Controller {
    @Inject
    Realm realm;

    @Inject
    MyRequestSparringStatusService mService;

    private MyRequestSparringStatusContract.View views;

    public MyRequestSparringStatusController(@NonNull AppComponent appComponent) {
        appComponent.inject(this);
    }

    public void setView(MyRequestSparringStatusContract.View view) {
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
        int idUser = getIdUser();
        if (idUser == 0) {
            views.getDataFailed("Please Signout and Signin again");
        } else {
            mService.getData(idUser);
        }
    }

    @Override
    public void getDataSuccess(List<MyRequestSparringStatus> data) {
        views.getDataSuccess(data);
    }

    @Override
    public void getDataFailed(String message) {
        views.getDataFailed(message);
    }
}
