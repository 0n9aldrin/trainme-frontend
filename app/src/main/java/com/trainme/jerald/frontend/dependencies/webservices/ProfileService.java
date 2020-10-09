package com.trainme.jerald.frontend.dependencies.webservices;

import android.util.Log;

import com.trainme.jerald.frontend.components.profile.ProfileContract;
import com.trainme.jerald.frontend.dependencies.component.AppComponent;
import com.trainme.jerald.frontend.dependencies.models.Profile;
import com.trainme.jerald.frontend.dependencies.response.ResponseGetProfile;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileService implements ProfileContract.Service {

    @Inject
    TrainmeAPI mApi;

    public ProfileService(AppComponent appComponent) {
        appComponent.inject(this);
    }

    public ProfileContract.Controller controller;

    public void instanceClass(ProfileContract.Controller controller){
        this.controller = controller;
    }

    @Override
    public void getData(int userId) {
        Call<ResponseGetProfile> call = mApi.getProfile(userId);
        call.enqueue(new Callback<ResponseGetProfile>() {
            @Override
            public void onResponse(Call<ResponseGetProfile> call, Response<ResponseGetProfile> response) {

                if(response.isSuccessful()){
                    if(response.body().isSuccess()){
                        controller.getDataSuccess(response.body().getData().get(0));
                    }else
                        controller.getDataFailed(response.body().getMessage());
                }else{
                    controller.getDataFailed("Server Error");
                }
            }

            @Override
            public void onFailure(Call<ResponseGetProfile> call, Throwable t) {
                Log.e("Failure", "onFailure");
                controller.getDataFailed(t.getMessage());
                t.printStackTrace();
            }
        });
    }
}
