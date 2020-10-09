package com.trainme.jerald.frontend.components.sparring;

import com.trainme.jerald.frontend.dependencies.response.model.SparringInvitation;

import java.util.List;

public class SparringContract {
    public interface View {
        void getDataSuccess(List<SparringInvitation> data);

        void getDataFailed(String message);
    }

    public interface Controller {
        void getData();

        void getDataSuccess(List<SparringInvitation> data);

        void getDataFailed(String message);
    }

    public interface Service {
        void getData();
    }
}
