package com.trainme.jerald.frontend.dependencies.webservices;

import android.util.Log;

import com.trainme.jerald.frontend.components.informasi.InformasiUmumContract;
import com.trainme.jerald.frontend.dependencies.component.AppComponent;
import com.trainme.jerald.frontend.dependencies.response.ResponseInformasiUmum;

import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Marthin on 9/20/2018.
 */

public class InformasiUmumService implements InformasiUmumContract.Repository {

    @Inject
    TrainmeAPI mApi;

    public InformasiUmumService(AppComponent appComponent) {
        appComponent.inject(this);
    }

    public InformasiUmumContract.Controller controller;

    public void instanceClass(InformasiUmumContract.Controller controller){
        this.controller = controller;
    }
    @Override
    public void getData() {
        Call<ResponseInformasiUmum> call = mApi.getInformasiUmum();
        call.enqueue(new Callback<ResponseInformasiUmum>() {
            @Override
            public void onResponse(Call<ResponseInformasiUmum> call, Response<ResponseInformasiUmum> response) {

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
            public void onFailure(Call<ResponseInformasiUmum> call, Throwable t) {
                Log.e("Failure", "onFailure");
                controller.getDataFailed(t.getMessage());
                t.printStackTrace();
            }
        });
    }
}
