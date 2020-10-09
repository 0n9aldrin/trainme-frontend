package com.trainme.jerald.frontend.dependencies.webservices;

import android.util.Log;

import com.trainme.jerald.frontend.components.approval.ApprovalSparringContract;
import com.trainme.jerald.frontend.dependencies.component.AppComponent;
import com.trainme.jerald.frontend.dependencies.models.ApprovalSparing;
import com.trainme.jerald.frontend.dependencies.response.ResponseApprovalSparing;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApprovalSparringService implements ApprovalSparringContract.Service {
    @Inject
    TrainmeAPI mService;

    private ApprovalSparringContract.Controller controller;

    public ApprovalSparringService(AppComponent appComponent) {
        appComponent.inject(this);
    }

    public void instanceClass(ApprovalSparringContract.Controller controller){
        this.controller = controller;
    }

    @Override
    public void approval(ApprovalSparing approvalSparing) {
        Call<ResponseApprovalSparing> call = mService.approveRequestSparing(approvalSparing);
        call.enqueue(new Callback<ResponseApprovalSparing>() {
            @Override
            public void onResponse(Call<ResponseApprovalSparing> call,
                                   Response<ResponseApprovalSparing> response) {
                if(response.isSuccessful()){
                    if(response.body().isSuccess())
                        controller.approvalSuccess(response.body().getMessage());
                    else
                        controller.approvalFailed(response.body().getMessage());
                }else{
                    controller.approvalFailed("Server Error");
                }
            }

            @Override
            public void onFailure(Call<ResponseApprovalSparing> call, Throwable t) {
                Log.e("Failure", "onFailure");
                controller.approvalFailed(t.getMessage());
                t.printStackTrace();
            }
        });
    }
}

