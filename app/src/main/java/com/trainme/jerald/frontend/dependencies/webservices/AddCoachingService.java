package com.trainme.jerald.frontend.dependencies.webservices;

import android.util.Log;

import com.trainme.jerald.frontend.components.addcoaching.AddCoachingContract;
import com.trainme.jerald.frontend.dependencies.component.AppComponent;
import com.trainme.jerald.frontend.dependencies.models.CoachingCreateModel;
import com.trainme.jerald.frontend.dependencies.response.ResponseCreateCoaching;
import com.trainme.jerald.frontend.dependencies.response.ResponseSparingInvitation;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCoachingService implements AddCoachingContract.Service {

    @Inject
    TrainmeAPI mService;

    private AddCoachingContract.Controller controller;

    public AddCoachingService(AppComponent appComponent) {
        appComponent.inject(this);
    }

    public void instanceClass(AddCoachingContract.Controller controller) {
        this.controller = controller;
    }


    @Override
    public void saveData(CoachingCreateModel model) {
        Call<ResponseCreateCoaching> call = mService.createCoaching(model);
        call.enqueue(new Callback<ResponseCreateCoaching>() {
            @Override
            public void onResponse(Call<ResponseCreateCoaching> call, Response<ResponseCreateCoaching> response) {
                if (response.isSuccessful()) {
                    if (response.body().isSuccess())
                        controller.saveDataSuccess();
                    else
                        controller.saveDataFailed(response.body().getMessage());
                } else {
                    controller.saveDataFailed("Server Error");
                }
            }

            @Override
            public void onFailure(Call<ResponseCreateCoaching> call, Throwable t) {
                Log.e("Failure", "onFailure");
                controller.saveDataFailed(t.getMessage());
                t.printStackTrace();
            }
        });
    }
}
