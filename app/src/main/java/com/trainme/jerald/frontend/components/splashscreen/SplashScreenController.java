package com.trainme.jerald.frontend.components.splashscreen;

import android.support.annotation.NonNull;

import com.trainme.jerald.frontend.dependencies.component.AppComponent;
import com.trainme.jerald.frontend.dependencies.response.model.User;
import com.trainme.jerald.frontend.dependencies.webservices.SliderServices;

import javax.inject.Inject;

import io.realm.Realm;

/**
 * Created by Marthin on 2/11/2018.
 */

public class SplashScreenController implements SplashScreenContract.Controller {
    @Inject
    SliderServices mService;

    @Inject
    Realm realm;

    private SplashScreenContract.View views;

    public SplashScreenController(@NonNull AppComponent appComponent) {
        appComponent.inject(this);
    }

    public void setView(SplashScreenContract.View view) {
        views = view;
    }

    @Override
    public void isLogin() {
        realm.beginTransaction();
        User user = realm.where(User.class).findFirst();
        realm.commitTransaction();

        if (user == null) {
            views.sessionUser(false);
        } else {
            views.sessionUser(true);
        }
    }

    @Override
    public void getSliderSuccess() {
        views.getSliderSuccess();
    }

    @Override
    public void getSliderFailed(String message) {
        views.getSliderFailed(message);
    }

    @Override
    public void getSlider() {
        mService.instanceClass(this);
        mService.getSlider();
    }
}
