package com.trainme.jerald.frontend.components.historycoaching;

import com.trainme.jerald.frontend.dependencies.response.model.SparringInvitation;

import java.util.List;

public class HistoryCoachingContract {
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
        void getData(int id);
    }
}
