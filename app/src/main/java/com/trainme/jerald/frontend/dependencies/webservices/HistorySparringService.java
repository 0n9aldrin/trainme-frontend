package com.trainme.jerald.frontend.dependencies.webservices;

import android.util.Log;

import com.trainme.jerald.frontend.components.historysparring.HistorySparringContract;
import com.trainme.jerald.frontend.dependencies.component.AppComponent;
import com.trainme.jerald.frontend.dependencies.response.ResponseMySparring;
import com.trainme.jerald.frontend.dependencies.response.ResponseSparingInvitation;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistorySparringService implements HistorySparringContract.Service {
    @Inject
    TrainmeAPI mService;

    private HistorySparringContract.Controller controller;

    public HistorySparringService(AppComponent appComponent) {
        appComponent.inject(this);
    }

    public void instanceClass(HistorySparringContract.Controller controller){
        this.controller = controller;
    }

    @Override
    public void getData(int id) {
        Call<ResponseMySparring> call = mService.getMySparringHistory(id);
        call.enqueue(new Callback<ResponseMySparring>() {
            @Override
            public void onResponse(Call<ResponseMySparring> call, Response<ResponseMySparring> response) {
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
            public void onFailure(Call<ResponseMySparring> call, Throwable t) {
                Log.e("Failure", "onFailure");
                controller.getDataFailed(t.getMessage());
                t.printStackTrace();
            }
        });
    }
}
