package com.trainme.jerald.frontend.components.addsparring;

import com.trainme.jerald.frontend.dependencies.models.SparringCreateModel;

public class AddSparringContract {
    public interface View {
        void saveDataSuccess();

        void saveDataFailed(String message);
    }

    public interface Controller {
        void saveData(SparringCreateModel model);

        void saveDataSuccess();

        void saveDataFailed(String message);
    }

    public interface Service {
        void saveData(SparringCreateModel model);
    }
}
