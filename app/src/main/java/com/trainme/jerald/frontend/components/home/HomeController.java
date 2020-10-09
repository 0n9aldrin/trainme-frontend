package com.trainme.jerald.frontend.components.home;

import android.support.annotation.NonNull;
import android.util.Log;

import com.trainme.jerald.frontend.dependencies.component.AppComponent;
import com.trainme.jerald.frontend.dependencies.response.model.AdsModel;
import com.trainme.jerald.frontend.dependencies.response.model.User;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;

public class HomeController implements HomeContract.Controller {

    @Inject
    Realm realm;

    private HomeContract.View views;

    public HomeController(@NonNull AppComponent appComponent) {
        appComponent.inject(this);
    }

    public void setView(HomeContract.View view) {
        views = view;
    }

    public int getIdRole(){
        realm.beginTransaction();
        User user = realm.where(User.class).findFirst();
        realm.commitTransaction();
        if (user == null) {
            return 0;
        } else {
            return Integer.valueOf(user.getRole());
        }
    }

    @Override
    public User getUser() {
        realm.beginTransaction();
        User user =realm.where(User.class).findFirst();
        realm.commitTransaction();
        return user;
    }

    @Override
    public void getAds() {
        realm.beginTransaction();
        List<AdsModel> allData = realm.where(AdsModel.class).findAll();
        realm.commitTransaction();
        if(allData.size()>0){
            views.getAdsSuccess(allData);
        }else {
            views.getAdsFailed("No data found");
        }
    }

    @Override
    public void signOut() {
        realm.executeTransactionAsync(realm -> {
            realm.delete(User.class);
        }, () -> {
            views.signOutSuccess();
        });

    }

}
