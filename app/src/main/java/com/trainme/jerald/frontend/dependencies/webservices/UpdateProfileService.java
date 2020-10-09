package com.trainme.jerald.frontend.dependencies.webservices;

import android.util.Log;

import com.trainme.jerald.frontend.components.profile.UpdateProfileContract;
import com.trainme.jerald.frontend.dependencies.component.AppComponent;
import com.trainme.jerald.frontend.dependencies.response.ResponseUpdateProfile;

import java.io.File;

import javax.inject.Inject;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProfileService implements UpdateProfileContract.Service {

    @Inject
    TrainmeAPI mApi;

    public UpdateProfileService(AppComponent appComponent) {
        appComponent.inject(this);
    }

    public UpdateProfileContract.Controller controller;

    public void instanceClass(UpdateProfileContract.Controller controller) {
        this.controller = controller;
    }

    @Override
    public void updateData(RequestBody data, RequestBody idUser) {
        Call<ResponseUpdateProfile> call = mApi.updateProfile(data, idUser);
        call.enqueue(new Callback<ResponseUpdateProfile>() {
            @Override
            public void onResponse(Call<ResponseUpdateProfile> call, Response<ResponseUpdateProfile> response) {

                if (response.isSuccessful()) {
                    if (response.body().isSuccess()) {
                        controller.updateDataSuccess();
                    } else
                        controller.updateDataFailed(response.body().getMessage());
                } else {
                    controller.updateDataFailed("Server Error");
                }
            }

            @Override
            public void onFailure(Call<ResponseUpdateProfile> call, Throwable t) {
                Log.e("Failure", "onFailure");
                controller.updateDataFailed(t.getMessage());
                t.printStackTrace();
            }
        });
    }
}
