package com.trainme.jerald.frontend.dependencies.webservices;

import android.util.Log;

import com.trainme.jerald.frontend.components.signup.SignupContract;
import com.trainme.jerald.frontend.dependencies.component.AppComponent;
import com.trainme.jerald.frontend.dependencies.models.SignupModel;
import com.trainme.jerald.frontend.dependencies.response.ResponseAuth;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupService implements SignupContract.Service {
    public SignupContract.Controller controller;

    @Inject
    TrainmeAPI mService;

    public SignupService(AppComponent appComponent) {
        appComponent.inject(this);
    }

    public void instanceClass(SignupContract.Controller controller){
        this.controller = controller;
    }

    @Override
    public void registerUser(SignupModel signupModel) {
        Log.e("User Model", signupModel.toString());
        Call<ResponseAuth> call = mService.registerUser(signupModel);
        call.enqueue(new Callback<ResponseAuth>() {
            @Override
            public void onResponse(Call<ResponseAuth> call, Response<ResponseAuth> response) {
                if(response.isSuccessful()){
                    if(response.body().isSuccess())
                        controller.registerSuccess("Register Success");
                    else
                        controller.registerFailed(response.body().getMessage());
                }else{
                    controller.registerFailed("Server Error");
                }
            }

            @Override
            public void onFailure(Call<ResponseAuth> call, Throwable t) {
                Log.e("Failure", "onFailure");
                controller.registerFailed(t.getMessage());
                t.printStackTrace();
            }
        });
    }
}
