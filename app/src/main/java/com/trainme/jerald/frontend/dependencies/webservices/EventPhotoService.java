package com.trainme.jerald.frontend.dependencies.webservices;

import android.util.Log;

import com.trainme.jerald.frontend.components.eventphoto.EventPhotoContract;
import com.trainme.jerald.frontend.dependencies.component.AppComponent;
import com.trainme.jerald.frontend.dependencies.response.ResponsePhotoEvent;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventPhotoService implements EventPhotoContract.Service {

    @Inject
    TrainmeAPI mApi;

    public EventPhotoService(AppComponent appComponent) {
        appComponent.inject(this);
    }

    public EventPhotoContract.Controller controller;

    public void instanceClass(EventPhotoContract.Controller controller){
        this.controller = controller;
    }

    @Override
    public void getData(int id) {
        Call<ResponsePhotoEvent> call = mApi.getPhotoEvent(id);
        call.enqueue(new Callback<ResponsePhotoEvent>() {
            @Override
            public void onResponse(Call<ResponsePhotoEvent> call, Response<ResponsePhotoEvent> response) {

                if(response.isSuccessful()){
                    if(response.body().isSuccess()){
                        controller.getDataSuccess(response.body().getData());
                    }else
                        controller.getDataFailed(response.body().getMessage());
                }else{
                    controller.getDataFailed("Server Error");
                }
            }

            @Override
            public void onFailure(Call<ResponsePhotoEvent> call, Throwable t) {
                Log.e("Failure", "onFailure");
                controller.getDataFailed(t.getMessage());
                t.printStackTrace();
            }
        });
    }
}
