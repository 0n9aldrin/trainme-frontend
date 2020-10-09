package com.trainme.jerald.frontend.dependencies.webservices;

import android.util.Log;

import com.trainme.jerald.frontend.components.myrequestsparringstatus.MyRequestSparringStatusContract;
import com.trainme.jerald.frontend.dependencies.component.AppComponent;
import com.trainme.jerald.frontend.dependencies.response.ResponseMyRequestSparring;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyRequestSparringStatusService implements MyRequestSparringStatusContract.Service {
    @Inject
    TrainmeAPI mService;

    private MyRequestSparringStatusContract.Controller controller;

    public MyRequestSparringStatusService(AppComponent appComponent) {
        appComponent.inject(this);
    }

    public void instanceClass(MyRequestSparringStatusContract.Controller controller){
        this.controller = controller;
    }

    @Override
    public void getData(int id) {
        Call<ResponseMyRequestSparring> call = mService.getAllMyRequestSparringStatus(id);
        call.enqueue(new Callback<ResponseMyRequestSparring>() {
            @Override
            public void onResponse(Call<ResponseMyRequestSparring> call, Response<ResponseMyRequestSparring> response) {
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
            public void onFailure(Call<ResponseMyRequestSparring> call, Throwable t) {
                Log.e("Failure", "onFailure");
                controller.getDataFailed(t.getMessage());
                t.printStackTrace();
            }
        });
    }
}
