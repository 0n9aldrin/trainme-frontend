package com.trainme.jerald.frontend.components.events;

import com.trainme.jerald.frontend.dependencies.models.AllEvents;

import java.util.List;

public class EventContract {
    public interface View {
        void getDataSuccess(List<AllEvents> data);
        void getDataFailed(String message);
    }

    public interface Controller {
        void getData();
        void getDataSuccess(List<AllEvents> data);
        void getDataFailed(String message);
    }

    public interface Service{
        void getData();
    }
}
