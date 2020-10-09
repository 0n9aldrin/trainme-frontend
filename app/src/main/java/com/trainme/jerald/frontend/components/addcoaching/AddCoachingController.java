package com.trainme.jerald.frontend.components.addcoaching;

import android.support.annotation.NonNull;

import com.trainme.jerald.frontend.dependencies.component.AppComponent;
import com.trainme.jerald.frontend.dependencies.models.CoachingCreateModel;
import com.trainme.jerald.frontend.dependencies.response.model.User;
import com.trainme.jerald.frontend.dependencies.webservices.AddCoachingService;
import com.trainme.jerald.frontend.dependencies.webservices.AddSparringService;

import javax.inject.Inject;

import io.realm.Realm;

public class AddCoachingController implements AddCoachingContract.Controller {

    @Inject
    Realm realm;

    @Inject
    AddCoachingService mService;

    private AddCoachingContract.View views;

    public AddCoachingController(@NonNull AppComponent appComponent) {
        appComponent.inject(this);
    }

    public void setView(AddCoachingContract.View view) {
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
    public void saveData(CoachingCreateModel model) {
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
