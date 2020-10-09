package com.trainme.jerald.frontend.components.myrequestsparringstatus;

import com.trainme.jerald.frontend.dependencies.response.model.MyRequestSparringStatus;

import java.util.List;

public class MyRequestSparringStatusContract {
    public interface View {
        void getDataSuccess(List<MyRequestSparringStatus> data);

        void getDataFailed(String message);
    }

    public interface Controller {
        void getData();

        void getDataSuccess(List<MyRequestSparringStatus> data);

        void getDataFailed(String message);
    }

    public interface Service {
        void getData(int id);
    }
}
