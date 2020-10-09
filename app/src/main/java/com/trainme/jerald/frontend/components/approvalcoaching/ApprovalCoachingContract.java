package com.trainme.jerald.frontend.components.approvalcoaching;

import com.trainme.jerald.frontend.dependencies.models.ApprovalSparing;

public class ApprovalCoachingContract {
    public interface View {
        void approvalCoachingSuccess(String message);

        void approvalCoachingFailed(String message);
    }

    public interface Controller {
        void approvalCoaching(ApprovalSparing approvalModel);

        void approvalCoachingSuccess(String message);

        void approvalCoachingFailed(String message);
    }

    public interface Service {
        void approvalCoaching(ApprovalSparing approvalModel);
    }
}
