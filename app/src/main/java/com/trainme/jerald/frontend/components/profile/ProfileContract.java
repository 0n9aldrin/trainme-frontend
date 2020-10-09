package com.trainme.jerald.frontend.components.profile;


import com.trainme.jerald.frontend.dependencies.models.Profile;

public class ProfileContract {
    public interface View {
        void getDataSuccess(Profile data);
        void getDataFailed(String message);
    }

    public interface Controller {
        void getData();
        void getDataSuccess(Profile data);
        void getDataFailed(String message);
    }

    public interface Service{
        void getData(int userId);
    }
}
