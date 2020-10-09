package com.trainme.jerald.frontend.components.addsparring;

import android.support.annotation.NonNull;

import com.trainme.jerald.frontend.dependencies.component.AppComponent;
import com.trainme.jerald.frontend.dependencies.models.SparringCreateModel;
import com.trainme.jerald.frontend.dependencies.response.model.User;
import com.trainme.jerald.frontend.dependencies.webservices.AddSparringService;

import javax.inject.Inject;

import io.realm.Realm;

public class AddSparringController implements AddSparringContract.Controller {

    @Inject
    Realm realm;

    @Inject
    AddSparringService mService;

    private AddSparringContract.View views;

    public AddSparringController(@NonNull AppComponent appComponent) {
        appComponent.inject(this);
    }

    public void setView(AddSparringContract.View view) {
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
    public void saveData(SparringCreateModel model) {
        mService.instanceClass(this);
        int idUser = getIdUser();
        if (idUser == 0) {
            views.saveDataFailed("Please Signout and Signin again");
        } else {
            model.setUserId(idUser);
            mService.saveData(model);
        }
    }

    @Override
    public void saveDataSuccess() {
        views.saveDataSuccess();
    }

    @Override
    public void saveDataFailed(String message) {
        views.saveDataFailed(message);
    }
}
