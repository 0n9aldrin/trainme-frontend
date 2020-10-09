package com.trainme.jerald.frontend.components.signin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.trainme.jerald.frontend.MainApplication;
import com.trainme.jerald.frontend.R;
import com.trainme.jerald.frontend.components.MainActivity;
import com.trainme.jerald.frontend.components.base.BaseActivity;
import com.trainme.jerald.frontend.components.signup.SignupActivity;
import com.trainme.jerald.frontend.dependencies.models.SigninModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SigninActivity extends BaseActivity implements SigninContract.View {

    @Inject
    SigninController mController;
    @BindView(R.id.etEmail)
    TextInputEditText etEmail;
    @BindView(R.id.etPassword)
    TextInputEditText etPassword;
    @BindView(R.id.btnLogin)
    Button btnLogin;

    @OnClick(R.id.btnLogin)
    void loginClicked() {
        if (TextUtils.isEmpty(etEmail.getText().toString())) {
            etEmail.setError(getString(R.string.error_email));
        } else {
            if (isNetworkConnected()) {
                showLoading();
                SigninModel loginModel = new SigninModel(etEmail.getText().toString(), etPassword.getText().toString());
                mController.setView(this, this);
                mController.signin(loginModel);
            } else {
                onError("No Internet Connection");
            }
        }

    }

    @BindView(R.id.btnRegister)
    TextView btnRegister;

    @OnClick(R.id.btnRegister)
    void registerClicked() {
        startActivity(SignupActivity.createIntent(getApplicationContext()));
        overridePendingTransition(R.transition.slide_left, R.transition.transition_do_nothing);
    }

    public static Intent createIntent(Context context) {
        return new Intent(context, SigninActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ((MainApplication) getApplication())
                .getComponent()
                .inject(this);
        ButterKnife.bind(this);
    }

    @Override
    public void signinSuccess(String message) {
        hideLoading();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        startActivity(MainActivity.createIntent(getApplicationContext()));
        overridePendingTransition(R.transition.fade_out, R.transition.transition_do_nothing);
    }

    @Override
    public void signinFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        hideLoading();
    }
}
