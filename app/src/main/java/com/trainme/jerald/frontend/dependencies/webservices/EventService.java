package com.trainme.jerald.frontend.dependencies.webservices;

import android.util.Log;

import com.trainme.jerald.frontend.components.events.EventContract;
import com.trainme.jerald.frontend.dependencies.component.AppComponent;
import com.trainme.jerald.frontend.dependencies.response.ResponseGetEvent;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Marthin on 9/20/2018.
 */

public class EventService implements EventContract.Service {

    @Inject
    TrainmeAPI mApi;

    public EventService(AppComponent appComponent) {
        appComponent.inject(this);
    }

    public EventContract.Controller controller;

    public void instanceClass(EventContract.Controller controller){
        this.controller = controller;
    }
    @Override
    public void getData() {
        Call<ResponseGetEvent> call = mApi.getEvents();
        call.enqueue(new Callback<ResponseGetEvent>() {
            @Override
            public void onResponse(Call<ResponseGetEvent> call, Response<ResponseGetEvent> response) {

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
            public void onFailure(Call<ResponseGetEvent> call, Throwable t) {
                Log.e("Failure", "onFailure");
                controller.getDataFailed(t.getMessage());
                t.printStackTrace();
            }
        });
    }
}
