package com.trainme.jerald.frontend.components.historysparring;

import com.trainme.jerald.frontend.dependencies.response.model.SparringInvitation;
import com.trainme.jerald.frontend.dependencies.response.model.SparringModel;

import java.util.List;

public class HistorySparringContract {
    public interface View {
        void getDataSuccess(List<SparringModel> data);

        void getDataFailed(String message);
    }

    public interface Controller {
        void getData();

        void getDataSuccess(List<SparringModel> data);

        void getDataFailed(String message);
    }

    public interface Service {
        void getData(int id);
    }
}
