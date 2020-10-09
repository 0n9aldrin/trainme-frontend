package com.trainme.jerald.frontend.dependencies.webservices;

import android.util.Log;

import com.trainme.jerald.frontend.components.requestjoin.RequestJoinContract;
import com.trainme.jerald.frontend.dependencies.component.AppComponent;
import com.trainme.jerald.frontend.dependencies.models.RequestJoinSparing;
import com.trainme.jerald.frontend.dependencies.response.ResponseRequestJoin;

import javax.inject.Inject;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestJoinService implements RequestJoinContract.Service {

    public RequestJoinContract.Controller controller;

    @Inject
    TrainmeAPI mService;

    @Inject
    Realm realm;

    public RequestJoinService(AppComponent appComponent) {
        appComponent.inject(this);
    }

    public void instanceClass(RequestJoinContract.Controller controller){
        this.controller = controller;
    }

    @Override
    public void requestJoin(RequestJoinSparing requestModel) {
        Call<ResponseRequestJoin> call = mService.requestJoinSparing(requestModel);
        call.enqueue(new Callback<ResponseRequestJoin>() {
            @Override
            public void onResponse(Call<ResponseRequestJoin> call, Response<ResponseRequestJoin> response) {
                if(response.isSuccessful()){
                    if(response.body().isSuccess()){
                        controller.requestJoinSuccess("Request Success");
                    }else
                        controller.requestJoinFailed(response.body().getMessage());
                }else{
                    controller.requestJoinFailed("Server Error");
                }
            }

            @Override
            public void onFailure(Call<ResponseRequestJoin> call, Throwable t) {
                Log.e("Failure", "onFailure");
                controller.requestJoinFailed(t.getMessage());
                t.printStackTrace();
            }
        });
    }
}
