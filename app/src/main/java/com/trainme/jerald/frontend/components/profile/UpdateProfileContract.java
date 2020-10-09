package com.trainme.jerald.frontend.components.profile;


import okhttp3.RequestBody;

public class UpdateProfileContract {
    public interface View {
        void updateDataSuccess();
        void updateDataFailed(String message);
    }

    public interface Controller {
        void updateData(RequestBody data, RequestBody idUser);
        void updateDataSuccess();
        void updateDataFailed(String message);
    }

    public interface Service{
        void updateData(RequestBody data, RequestBody idUser);
    }
}
