package com.trainme.jerald.frontend.dependencies.webservices;

import android.util.Log;


import com.trainme.jerald.frontend.components.myrequestcoachingstatus.MyRequestCoachingStatusContract;
import com.trainme.jerald.frontend.dependencies.component.AppComponent;
import com.trainme.jerald.frontend.dependencies.response.ResponseMyRequestSparring;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyRequestCoachingStatusService implements MyRequestCoachingStatusContract.Service {
    @Inject
    TrainmeAPI mService;

    private MyRequestCoachingStatusContract.Controller controller;

    public MyRequestCoachingStatusService(AppComponent appComponent) {
        appComponent.inject(this);
    }

    public void instanceClass(MyRequestCoachingStatusContract.Controller controller){
        this.controller = controller;
    }

    @Override
    public void getData(int id) {
        Log.e("Called"," myrequest coaching"+id);
        Call<ResponseMyRequestSparring> call = mService.getAllMyRequestCoachingStatus(id);
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
