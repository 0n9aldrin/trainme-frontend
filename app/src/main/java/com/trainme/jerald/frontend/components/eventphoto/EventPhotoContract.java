package com.trainme.jerald.frontend.components.eventphoto;

import com.trainme.jerald.frontend.dependencies.models.EventPhoto;

import java.util.List;

public class EventPhotoContract {
    public interface View {
        void getDataSuccess(List<EventPhoto> data);
        void getDataFailed(String message);
    }

    public interface Controller {
        void getData(int id);
        void getDataSuccess(List<EventPhoto> data);
        void getDataFailed(String message);
    }

    public interface Service{
        void getData(int id);
    }
}