package com.trainme.jerald.frontend.dependencies.webservices;

import android.util.Log;

import com.trainme.jerald.frontend.components.ranking.RankingContract;
import com.trainme.jerald.frontend.dependencies.component.AppComponent;
import com.trainme.jerald.frontend.dependencies.response.ResponseRanking;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RankingWomanService implements RankingContract.Service {

    @Inject
    TrainmeAPI mService;

    private RankingContract.Controller controller;

    public RankingWomanService(AppComponent appComponent) {
        appComponent.inject(this);
    }

    public void instanceClass(RankingContract.Controller controller){
        this.controller = controller;
    }

    @Override
    public void getData() {
        Call<ResponseRanking> call = mService.getWomanRanking();
        call.enqueue(new Callback<ResponseRanking>() {
            @Override
            public void onResponse(Call<ResponseRanking> call, Response<ResponseRanking> response) {
                if(response.isSuccessful()){
                    if(response.body().isSuccess())
                        controller.getDataSuccess(response.body().getData());
                    else
                        controller.getDataFailed(response.body().getMessage());
                }else{
                    controller.getDataFailed("Server Error");
                }
            }

            @Override
            public void onFailure(Call<ResponseRanking> call, Throwable t) {
                Log.e("Failure", "onFailure");
                controller.getDataFailed(t.getMessage());
                t.printStackTrace();
            }
        });
    }
}
