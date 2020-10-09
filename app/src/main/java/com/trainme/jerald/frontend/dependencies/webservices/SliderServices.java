package com.trainme.jerald.frontend.dependencies.webservices;

import com.trainme.jerald.frontend.components.splashscreen.SplashScreenContract;
import com.trainme.jerald.frontend.dependencies.component.AppComponent;
import com.trainme.jerald.frontend.dependencies.response.ResponseGetAds;

import javax.inject.Inject;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SliderServices implements SplashScreenContract.Repository {

    @Inject
    TrainmeAPI mService;

    @Inject
    Realm realm;


    public SliderServices(AppComponent appComponent) {
        appComponent.inject(this);
    }
    public SplashScreenContract.Controller controller;

    public void instanceClass(SplashScreenContract.Controller controller){
        this.controller = controller;
    }

    @Override
    public void getSlider() {
        Call<ResponseGetAds> call = mService.getAds();
        call.enqueue(new Callback<ResponseGetAds>() {
            @Override
            public void onResponse(Call<ResponseGetAds> call, Response<ResponseGetAds> response) {
                if(response.isSuccessful()){
                    assert response.body() != null;
                    if(response.body().isSuccess()){
                        realm.beginTransaction();
                        realm.executeTransactionAsync(realmuser -> {
                            realmuser.insertOrUpdate(response.body().getData());
                        });
                        realm.commitTransaction();
                        controller.getSliderSuccess();
                    }else
                        controller.getSliderFailed(response.body().getMessage());
                }else{
                    controller.getSliderFailed(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseGetAds> call, Throwable t) {
                controller.getSliderFailed(t.getMessage());
                t.printStackTrace();
            }
        });
    }
}

