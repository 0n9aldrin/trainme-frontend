package com.trainme.jerald.frontend.components.events;

import android.support.annotation.NonNull;

import com.trainme.jerald.frontend.dependencies.component.AppComponent;
import com.trainme.jerald.frontend.dependencies.models.AllEvents;
import com.trainme.jerald.frontend.dependencies.webservices.EventService;

import java.util.List;

import javax.inject.Inject;

public class EventController implements EventContract.Controller {

    @Inject
    EventService mService;

    private EventContract.View views;

    public EventController(@NonNull AppComponent appComponent) {
        appComponent.inject(this);
    }

    public void setView(EventContract.View view){
        views = view;
    }

    @Override
    public void getData() {
        mService.instanceClass(this);
        mService.getData();
    }

    @Override
    public void getDataSuccess(List<AllEvents> data) {
        views.getDataSuccess(data);
    }

    @Override
    public void getDataFailed(String message) {
        views.getDataFailed(message);
    }
}
