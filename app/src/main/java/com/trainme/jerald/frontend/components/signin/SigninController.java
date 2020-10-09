package com.trainme.jerald.frontend.components.signin;

import android.content.Context;
import android.support.annotation.NonNull;

import com.trainme.jerald.frontend.dependencies.component.AppComponent;
import com.trainme.jerald.frontend.dependencies.models.SigninModel;
import com.trainme.jerald.frontend.dependencies.webservices.SigninService;

import javax.inject.Inject;

public class SigninController implements SigninContract.Controller {

    @Inject
    SigninService mService;

    private SigninContract.View views;

    private Context mContext;

    public SigninController(@NonNull AppComponent appComponent) {
        appComponent.inject(this);
    }

    public void setView(SigninContract.View view, Context mContext) {
        views = view;
        this.mContext = mContext;
    }
    @Override
    public void signin(SigninModel signinModel) {
        mService.instanceClass(this);
        mService.signin(signinModel);
    }

    @Override
    public void signinSuccess(String message) {
        views.signinSuccess(message);
    }

    @Override
    public void signinFailed(String message) {
        views.signinFailed(message);
    }
}
