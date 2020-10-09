package com.trainme.jerald.frontend.components.stucoaching;

import com.trainme.jerald.frontend.dependencies.response.model.SparringInvitation;

import java.util.List;

public class StudentCoachingContract {
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
