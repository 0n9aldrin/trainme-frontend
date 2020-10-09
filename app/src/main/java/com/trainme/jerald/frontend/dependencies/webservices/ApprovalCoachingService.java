package com.trainme.jerald.frontend.dependencies.webservices;

import android.util.Log;


import com.trainme.jerald.frontend.components.approvalcoaching.ApprovalCoachingContract;
import com.trainme.jerald.frontend.dependencies.component.AppComponent;
import com.trainme.jerald.frontend.dependencies.models.ApprovalSparing;
import com.trainme.jerald.frontend.dependencies.response.ResponseApprovalSparing;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApprovalCoachingService implements ApprovalCoachingContract.Service {
    @Inject
    TrainmeAPI mService;

    private ApprovalCoachingContract.Controller controller;

    public ApprovalCoachingService(AppComponent appComponent) {
        appComponent.inject(this);
    }

    public void instanceClass(ApprovalCoachingContract.Controller controller){
        this.controller = controller;
    }

    @Override
    public void approvalCoaching(ApprovalSparing approvalSparing) {
        Call<ResponseApprovalSparing> call = mService.approveRequestCoaching(approvalSparing);
        call.enqueue(new Callback<ResponseApprovalSparing>() {
            @Override
            public void onResponse(Call<ResponseApprovalSparing> call,
                                   Response<ResponseApprovalSparing> response) {
                if(response.isSuccessful()){
                    if(response.body().isSuccess())
                        controller.approvalCoachingSuccess(response.body().getMessage());
                    else
                        controller.approvalCoachingFailed(response.body().getMessage());
                }else{
                    controller.approvalCoachingFailed("Server Error");
                }
            }

            @Override
            public void onFailure(Call<ResponseApprovalSparing> call, Throwable t) {
                Log.e("Failure", "onFailure");
                controller.approvalCoachingFailed(t.getMessage());
                t.printStackTrace();
            }
        });
    }
}

