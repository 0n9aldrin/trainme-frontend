package com.trainme.jerald.frontend.dependencies.webservices;

import android.util.Log;

import com.trainme.jerald.frontend.components.informasi.KebijakanPrivasiContract;
import com.trainme.jerald.frontend.dependencies.component.AppComponent;
import com.trainme.jerald.frontend.dependencies.response.ResponseKebijakanPrivasi;

import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Marthin on 9/20/2018.
 */

public class KebijakanPrivasiService implements KebijakanPrivasiContract.Repository {
    @Inject
    TrainmeAPI mApi;

    public KebijakanPrivasiService(AppComponent appComponent) {
        appComponent.inject(this);
    }
    public KebijakanPrivasiContract.Controller controller;

    public void instanceClass(KebijakanPrivasiContract.Controller controller){
        this.controller = controller;
    }
    @Override
    public void getData() {
        Call<ResponseKebijakanPrivasi> call = mApi.getKebijakanPrivasi();
        call.enqueue(new Callback<ResponseKebijakanPrivasi>() {
            @Override
            public void onResponse(Call<ResponseKebijakanPrivasi> call, Response<ResponseKebijakanPrivasi> response) {

                if(response.isSuccessful()){
                    if(response.body().isSuccess()){
                        controller.getDataSuccess(response.body().getData());
                    }else {
                        Log.e("eror",response.body().getMessage());
                        controller.getDataFailed(response.body().getMessage());
                    }

                }else{
                    controller.getDataFailed("Server Error");
                }
            }

            @Override
            public void onFailure(Call<ResponseKebijakanPrivasi> call, Throwable t) {
                Log.e("Failure", "onFailure"+t.getMessage());
                controller.getDataFailed(t.getMessage());
                t.printStackTrace();
            }
        });
    }
}