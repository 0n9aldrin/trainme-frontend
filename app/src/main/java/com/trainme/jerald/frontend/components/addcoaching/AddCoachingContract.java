package com.trainme.jerald.frontend.components.addcoaching;


import com.trainme.jerald.frontend.dependencies.models.CoachingCreateModel;

public class AddCoachingContract {
    public interface View {
        void saveDataSuccess();

        void saveDataFailed(String message);
    }

    public interface Controller {
        void saveData(CoachingCreateModel model);

        void saveDataSuccess();

        void saveDataFailed(String message);
    }

    public interface Service {
        void saveData(CoachingCreateModel model);
    }
}
