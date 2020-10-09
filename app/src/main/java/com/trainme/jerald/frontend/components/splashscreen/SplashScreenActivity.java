package com.trainme.jerald.frontend.components.splashscreen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.trainme.jerald.frontend.MainApplication;
import com.trainme.jerald.frontend.R;
import com.trainme.jerald.frontend.components.MainActivity;
import com.trainme.jerald.frontend.components.base.BaseActivity;
import com.trainme.jerald.frontend.components.signin.SigninActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class SplashScreenActivity extends BaseActivity implements SplashScreenContract.View {

    @Inject
    SplashScreenController controller;

    public static Intent createIntent(Context context) {
        return new Intent(context, SplashScreenActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainApplication) getApplication())
                .getComponent()
                .inject(this);
        ButterKnife.bind(this);
        controller.setView(this);
        controller.getSlider();
    }

    private void checkSesion(){
        if(isNetworkConnected()){
            showLoading();
            controller.isLogin();
        }else{
            onError(getString(R.string.network_error));
        }
    }

    @Override
    public void sessionUser(boolean result) {
        hideLoading();
        if(result)
            startActivity(MainActivity.createIntent(getApplicationContext()));
        else
            startActivity(SigninActivity.createIntent(getApplicationContext()));
    }

    @Override
    public void getSliderSuccess() {
        checkSesion();
    }

    @Override
    public void getSliderFailed(String message) {
        onError(getString(R.string.error_occured));
        checkSesion();
    }
}
