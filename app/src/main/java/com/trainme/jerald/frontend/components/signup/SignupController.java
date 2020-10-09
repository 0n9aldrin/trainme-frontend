package com.trainme.jerald.frontend.components.signup;

import android.content.Context;
import android.support.annotation.NonNull;

import com.trainme.jerald.frontend.dependencies.component.AppComponent;
import com.trainme.jerald.frontend.dependencies.models.SignupModel;
import com.trainme.jerald.frontend.dependencies.webservices.SignupService;

import javax.inject.Inject;

public class SignupController implements SignupContract.Controller {

    @Inject
    SignupService mService;

    private SignupContract.View views;

    private Context mContext;

    public SignupController(@NonNull AppComponent appComponent) {
        appComponent.inject(this);
    }

    public void setView(SignupContract.View view, Context mContext) {
        views = view;
        this.mContext = mContext;
    }
    @Override
    public void registerUser(SignupModel signupModel) {
        mService.instanceClass(this);
        mService.registerUser(signupModel);
    }

    @Override
    public void registerSuccess(String message) {
        views.registerSuccess(message);
    }

    @Override
    public void registerFailed(String message) {
        views.registerFailed(message);
    }
}
