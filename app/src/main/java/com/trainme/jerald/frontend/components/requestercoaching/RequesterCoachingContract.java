package com.trainme.jerald.frontend.components.requestercoaching;

import com.trainme.jerald.frontend.dependencies.response.model.RequesterSparring;

import java.util.List;

public class RequesterCoachingContract {
    public interface View {
        void getDataSuccess(List<RequesterSparring> data);

        void getDataFailed(String message);
    }

    public interface Controller {
        void getData(int ids);

        void getDataSuccess(List<RequesterSparring> data);

        void getDataFailed(String message);
    }

    public interface Service {
        void getData(int id);
    }
}
