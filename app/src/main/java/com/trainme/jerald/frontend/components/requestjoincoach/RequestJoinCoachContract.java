package com.trainme.jerald.frontend.components.requestjoincoach;

import com.trainme.jerald.frontend.dependencies.models.RequestJoinSparing;

public class RequestJoinCoachContract {
    public interface View {
        void requestJoinCoachSuccess(String message);

        void requestJoinCoachFailed(String message);
    }

    public interface Controller {
        void requestJoinCoach(RequestJoinSparing requestModel);

        void requestJoinCoachSuccess(String message);

        void requestJoinCoachFailed(String message);
    }

    public interface Service {
        void requestJoinCoach(RequestJoinSparing requestModel);
    }
}
