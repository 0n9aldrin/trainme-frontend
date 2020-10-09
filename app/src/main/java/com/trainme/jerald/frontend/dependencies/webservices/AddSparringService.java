package com.trainme.jerald.frontend.dependencies.webservices;

import android.util.Log;

import com.trainme.jerald.frontend.components.addsparring.AddSparringContract;
import com.trainme.jerald.frontend.dependencies.component.AppComponent;
import com.trainme.jerald.frontend.dependencies.models.SparringCreateModel;
import com.trainme.jerald.frontend.dependencies.response.ResponseCreateSparring;
import com.trainme.jerald.frontend.dependencies.response.ResponseSparingInvitation;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSparringService implements AddSparringContract.Service {

    @Inject
    TrainmeAPI mService;

    private AddSparringContract.Controller controller;

    public AddSparringService(AppComponent appComponent) {
        appComponent.inject(this);
    }

    public void instanceClass(AddSparringContract.Controller controller){
        this.controller = controller;
    }


    @Override
    public void saveData(SparringCreateModel model) {
        Call<ResponseCreateSparring> call = mService.createSparing(model);
        call.enqueue(new Callback<ResponseCreateSparring>() {
            @Override
            public void onResponse(Call<ResponseCreateSparring> call, Response<ResponseCreateSparring> response) {
                if(response.isSuccessful()){
                    if(response.body().isSuccess())
                        controller.saveDataSuccess();
                    else
                        controller.saveDataFailed(response.body().getMessage());
                }else{
                    controller.saveDataFailed("Server Error");
                }
            }

            @Override
            public void onFailure(Call<ResponseCreateSparring> call, Throwable t) {
                Log.e("Failure", "onFailure");
                controller.saveDataFailed(t.getMessage());
                t.printStackTrace();
            }
        });
    }
}
