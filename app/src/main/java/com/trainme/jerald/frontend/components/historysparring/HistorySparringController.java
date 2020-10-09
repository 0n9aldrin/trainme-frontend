package com.trainme.jerald.frontend.components.historysparring;

import android.support.annotation.NonNull;

import com.trainme.jerald.frontend.dependencies.component.AppComponent;
import com.trainme.jerald.frontend.dependencies.response.model.SparringInvitation;
import com.trainme.jerald.frontend.dependencies.response.model.SparringModel;
import com.trainme.jerald.frontend.dependencies.response.model.User;
import com.trainme.jerald.frontend.dependencies.webservices.HistorySparringService;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;

public class HistorySparringController implements HistorySparringContract.Controller {
    @Inject
    Realm realm;

    @Inject
    HistorySparringService mService;

    private HistorySparringContract.View views;

    public HistorySparringController(@NonNull AppComponent appComponent) {
        appComponent.inject(this);
    }

    public void setView(HistorySparringContract.View view) {
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
    public void getDataSuccess(List<SparringModel> data) {
        views.getDataSuccess(data);
    }

    @Override
    public void getDataFailed(String message) {
        views.getDataFailed(message);
    }
}
