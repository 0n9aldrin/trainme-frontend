package com.trainme.jerald.frontend.dependencies.webservices;

import android.util.Log;


import com.trainme.jerald.frontend.components.requestjoincoach.RequestJoinCoachContract;
import com.trainme.jerald.frontend.dependencies.component.AppComponent;
import com.trainme.jerald.frontend.dependencies.models.RequestJoinSparing;
import com.trainme.jerald.frontend.dependencies.response.ResponseRequestJoin;

import javax.inject.Inject;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestJoinCoachService implements RequestJoinCoachContract.Service {

    public RequestJoinCoachContract.Controller controller;

    @Inject
    TrainmeAPI mService;

    public RequestJoinCoachService(AppComponent appComponent) {
        appComponent.inject(this);
    }

    public void instanceClass(RequestJoinCoachContract.Controller controller){
        this.controller = controller;
    }

    @Override
    public void requestJoinCoach(RequestJoinSparing requestModel) {
        Call<ResponseRequestJoin> call = mService.requestJoinCoaching(requestModel);
        call.enqueue(new Callback<ResponseRequestJoin>() {
            @Override
            public void onResponse(Call<ResponseRequestJoin> call, Response<ResponseRequestJoin> response) {
                if(response.isSuccessful()){
                    if(response.body().isSuccess()){
                        controller.requestJoinCoachSuccess("Request Success");
                    }else
                        controller.requestJoinCoachFailed(response.body().getMessage());
                }else{
                    controller.requestJoinCoachFailed("Server Error");
                }
            }

            @Override
            public void onFailure(Call<ResponseRequestJoin> call, Throwable t) {
                Log.e("Failure", "onFailure");
                controller.requestJoinCoachFailed(t.getMessage());
                t.printStackTrace();
            }
        });
    }
}
