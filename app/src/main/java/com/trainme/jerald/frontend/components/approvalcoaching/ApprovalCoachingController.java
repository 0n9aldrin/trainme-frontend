package com.trainme.jerald.frontend.components.approvalcoaching;

import android.support.annotation.NonNull;

import com.trainme.jerald.frontend.dependencies.component.AppComponent;
import com.trainme.jerald.frontend.dependencies.models.ApprovalSparing;
import com.trainme.jerald.frontend.dependencies.webservices.ApprovalCoachingService;
import com.trainme.jerald.frontend.dependencies.webservices.ApprovalSparringService;

import javax.inject.Inject;

public class ApprovalCoachingController implements ApprovalCoachingContract.Controller {

    @Inject
    ApprovalCoachingService mService;

    private ApprovalCoachingContract.View views;

    public ApprovalCoachingController(@NonNull AppComponent appComponent) {
        appComponent.inject(this);
    }

    public void setView(ApprovalCoachingContract.View view) {
        views = view;
    }

    @Override
    public void approvalCoaching(ApprovalSparing approvalSparing) {
        mService.instanceClass(this);
        mService.approvalCoaching(approvalSparing);
    }

    @Override
    public void approvalCoachingSuccess(String message) {
        views.approvalCoachingSuccess(message);
    }

    @Override
    public void approvalCoachingFailed(String message) {
        views.approvalCoachingFailed(message);
    }
}
