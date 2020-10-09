package com.trainme.jerald.frontend.dependencies.webservices;

import android.util.Log;

import com.trainme.jerald.frontend.components.requestersparring.RequesterSparringContract;
import com.trainme.jerald.frontend.dependencies.component.AppComponent;
import com.trainme.jerald.frontend.dependencies.response.ResponseAllRequesterSparring;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequesterSparringService implements RequesterSparringContract.Service {
    @Inject
    TrainmeAPI mService;

    private RequesterSparringContract.Controller controller;

    public RequesterSparringService(AppComponent appComponent) {
        appComponent.inject(this);
    }

    public void instanceClass(RequesterSparringContract.Controller controller){
        this.controller = controller;
    }

    @Override
    public void getData(int id) {
        Call<ResponseAllRequesterSparring> call = mService.getAllRequesterBySparring(id);
        call.enqueue(new Callback<ResponseAllRequesterSparring>() {
            @Override
            public void onResponse(Call<ResponseAllRequesterSparring> call,
                                   Response<ResponseAllRequesterSparring> response) {
                if(response.isSuccessful()){
                    if(response.body().isSuccess())
                        controller.getDataSuccess(response.body().getData());
                    else
                        controller.getDataFailed(response.body().getMessage());
                }else{
                    controller.getDataFailed("Server Error");
                }
            }

            @Override
            public void onFailure(Call<ResponseAllRequesterSparring> call, Throwable t) {
                Log.e("Failure", "onFailure");
                controller.getDataFailed(t.getMessage());
                t.printStackTrace();
            }
        });
    }
}

