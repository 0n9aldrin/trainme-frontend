package com.trainme.jerald.frontend.components.approval;

import com.trainme.jerald.frontend.dependencies.models.ApprovalSparing;
import com.trainme.jerald.frontend.dependencies.models.RequestJoinSparing;

public class ApprovalSparringContract {
    public interface View {
        void approvalSuccess(String message);

        void approvalFailed(String message);
    }

    public interface Controller {
        void approval(ApprovalSparing approvalModel);

        void approvalSuccess(String message);

        void approvalFailed(String message);
    }

    public interface Service {
        void approval(ApprovalSparing approvalModel);
    }
}
