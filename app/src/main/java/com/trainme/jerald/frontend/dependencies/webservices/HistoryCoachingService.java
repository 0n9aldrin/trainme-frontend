package com.trainme.jerald.frontend.dependencies.webservices;

import android.util.Log;

import com.trainme.jerald.frontend.components.historycoaching.HistoryCoachingContract;
import com.trainme.jerald.frontend.dependencies.component.AppComponent;
import com.trainme.jerald.frontend.dependencies.response.ResponseSparingInvitation;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryCoachingService implements HistoryCoachingContract.Service {
    @Inject
    TrainmeAPI mService;

    private HistoryCoachingContract.Controller controller;

    public HistoryCoachingService(AppComponent appComponent) {
        appComponent.inject(this);
    }

    public void instanceClass(HistoryCoachingContract.Controller controller){
        this.controller = controller;
    }

    @Override
    public void getData(int id) {
        Call<ResponseSparingInvitation> call = mService.getHistoryCoaching(id);
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
