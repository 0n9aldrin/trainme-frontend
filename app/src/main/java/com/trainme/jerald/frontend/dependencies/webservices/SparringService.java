package com.trainme.jerald.frontend.dependencies.webservices;

import android.util.Log;

import com.trainme.jerald.frontend.components.sparring.SparringContract;
import com.trainme.jerald.frontend.dependencies.component.AppComponent;
import com.trainme.jerald.frontend.dependencies.response.ResponseSparingInvitation;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SparringService implements SparringContract.Service {
    @Inject
    TrainmeAPI mService;

    private SparringContract.Controller controller;

    public SparringService(AppComponent appComponent) {
        appComponent.inject(this);
    }

    public void instanceClass(SparringContract.Controller controller){
        this.controller = controller;
    }

    @Override
    public void getData() {
        Call<ResponseSparingInvitation> call = mService.getAllSparring();
        call.enqueue(new Callback<ResponseSparingInvitation>() {
            @Override
            public void onResponse(Call<ResponseSparingInvitation> call, Response<ResponseSparingInvitation> response) {
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
            public void onFailure(Call<ResponseSparingInvitation> call, Throwable t) {
                Log.e("Failure", "onFailure");
                controller.getDataFailed(t.getMessage());
                t.printStackTrace();
            }
        });
    }
}
