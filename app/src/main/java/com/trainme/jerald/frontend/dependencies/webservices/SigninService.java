package com.trainme.jerald.frontend.dependencies.webservices;

import android.util.Log;

import com.trainme.jerald.frontend.components.signin.SigninContract;
import com.trainme.jerald.frontend.components.signup.SignupContract;
import com.trainme.jerald.frontend.dependencies.component.AppComponent;
import com.trainme.jerald.frontend.dependencies.models.SigninModel;
import com.trainme.jerald.frontend.dependencies.response.ResponseAuth;

import javax.inject.Inject;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SigninService implements SigninContract.Service {

    public SigninContract.Controller controller;

    @Inject
    TrainmeAPI mService;

    @Inject
    Realm realm;

    public SigninService(AppComponent appComponent) {
        appComponent.inject(this);
    }

    public void instanceClass(SigninContract.Controller controller){
        this.controller = controller;
    }

    @Override
    public void signin(SigninModel signinModel) {
        Call<ResponseAuth> call = mService.loginUser(signinModel);
        call.enqueue(new Callback<ResponseAuth>() {
            @Override
            public void onResponse(Call<ResponseAuth> call, Response<ResponseAuth> response) {
                if(response.isSuccessful()){
                    Log.e("Response", response.body().getMessage());
                    if(response.body().isSuccess()){
                        realm.beginTransaction();
                        realm.executeTransactionAsync(realmuser -> {
                            realmuser.insertOrUpdate(response.body().getData());
                        });
                        realm.commitTransaction();
                        controller.signinSuccess("Signin Success");
                    }else {
                        controller.signinFailed(response.body().getMessage());
                    }
                }else{
                    controller.signinFailed("Server Error");
                }
            }

            @Override
            public void onFailure(Call<ResponseAuth> call, Throwable t) {
                Log.e("Failure", "onFailure");
                controller.signinFailed(t.getMessage());
                t.printStackTrace();
            }
        });
    }
}
