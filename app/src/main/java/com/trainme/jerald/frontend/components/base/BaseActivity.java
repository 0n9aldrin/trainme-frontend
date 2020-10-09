package com.trainme.jerald.frontend.components.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.trainme.jerald.frontend.R;
import com.trainme.jerald.frontend.components.splashscreen.SplashScreenActivity;
import com.trainme.jerald.frontend.contracts.MVPView;
import com.trainme.jerald.frontend.utils.CommonUtils;
import com.trainme.jerald.frontend.utils.Message;
import com.trainme.jerald.frontend.utils.NetworkUtils;


/**
 * Created by Marthin on 2/20/2018.
 */

public class BaseActivity extends AppCompatActivity implements MVPView {
    private ProgressDialog mProgressDialog;
    private View rootView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = getWindow().getDecorView().getRootView();
    }

    @Override
    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(this);
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }


    @Override
    public void openActivityOnTokenExpire() {
        startActivity(SplashScreenActivity.createIntent(this));
        finish();
    }

    @Override
    public void onError(int resId) {
        onError(getString(resId));
    }

    @Override
    public void onError(String message) {
        if (message != null) {
            Message.showSnackBar(message, getApplicationContext(), rootView);
        } else {
            Message.showSnackBar(getString(R.string.some_error), getApplicationContext(), rootView);
        }
    }

    @Override
    public void showMessage(String message) {
        if (message != null) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getString(R.string.some_error), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showMessage(int resId) {
        showMessage(getString(resId));
    }

    @Override
    public boolean isNetworkConnected() {
        boolean result = NetworkUtils.isNetworkConnected(getApplicationContext());
        return result;
    }

    @Override
    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
