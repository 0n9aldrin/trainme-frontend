package com.trainme.jerald.frontend.components.historycoaching;

import android.support.annotation.NonNull;

import com.trainme.jerald.frontend.dependencies.component.AppComponent;
import com.trainme.jerald.frontend.dependencies.response.model.SparringInvitation;
import com.trainme.jerald.frontend.dependencies.response.model.User;
import com.trainme.jerald.frontend.dependencies.webservices.HistoryCoachingService;
import com.trainme.jerald.frontend.dependencies.webservices.HistorySparringService;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;

public class HistoryCoachingController implements HistoryCoachingContract.Controller {
    @Inject
    Realm realm;

    @Inject
    HistoryCoachingService mService;

    private HistoryCoachingContract.View views;

    public HistoryCoachingController(@NonNull AppComponent appComponent) {
        appComponent.inject(this);
    }

    public void setView(HistoryCoachingContract.View view) {
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
    public void getDataSuccess(List<SparringInvitation> data) {
        views.getDataSuccess(data);
    }

    @Override
    public void getDataFailed(String message) {
        views.getDataFailed(message);
    }
}
