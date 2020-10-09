package com.trainme.jerald.frontend.components.approval;

import android.support.annotation.NonNull;

import com.trainme.jerald.frontend.dependencies.component.AppComponent;
import com.trainme.jerald.frontend.dependencies.models.ApprovalSparing;
import com.trainme.jerald.frontend.dependencies.models.RequestJoinSparing;
import com.trainme.jerald.frontend.dependencies.webservices.ApprovalSparringService;
import com.trainme.jerald.frontend.dependencies.webservices.RequestJoinService;

import javax.inject.Inject;

public class ApprovalSparringController implements ApprovalSparringContract.Controller {

    @Inject
    ApprovalSparringService mService;

    private ApprovalSparringContract.View views;

    public ApprovalSparringController(@NonNull AppComponent appComponent) {
        appComponent.inject(this);
    }

    public void setView(ApprovalSparringContract.View view) {
        views = view;
    }

    @Override
    public void approval(ApprovalSparing approvalSparing) {
        mService.instanceClass(this);
        mService.approval(approvalSparing);
    }

    @Override
    public void approvalSuccess(String message) {
        views.approvalSuccess(message);
    }

    @Override
    public void approvalFailed(String message) {
        views.approvalFailed(message);
    }
}
