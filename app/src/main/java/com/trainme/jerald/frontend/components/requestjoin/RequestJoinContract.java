package com.trainme.jerald.frontend.components.requestjoin;

import com.trainme.jerald.frontend.dependencies.models.RequestJoinSparing;

public class RequestJoinContract {
    public interface View {
        void requestJoinSuccess(String message);

        void requestJoinFailed(String message);
    }

    public interface Controller {
        void requestJoin(RequestJoinSparing requestModel);

        void requestJoinSuccess(String message);

        void requestJoinFailed(String message);
    }

    public interface Service {
        void requestJoin(RequestJoinSparing requestModel);
    }
}
