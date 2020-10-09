package com.trainme.jerald.frontend.components.eventphoto;

import android.support.annotation.NonNull;

import com.trainme.jerald.frontend.dependencies.component.AppComponent;
import com.trainme.jerald.frontend.dependencies.models.EventPhoto;
import com.trainme.jerald.frontend.dependencies.webservices.EventPhotoService;

import java.util.List;

import javax.inject.Inject;

public class EventPhotoController implements EventPhotoContract.Controller {

    @Inject
    EventPhotoService mService;

    private EventPhotoContract.View views;

    public EventPhotoController(@NonNull AppComponent appComponent) {
        appComponent.inject(this);
    }

    public void setView(EventPhotoContract.View view){
        views = view;
    }

    @Override
    public void getData(int id) {
        mService.instanceClass(this);
        mService.getData(id);
    }

    @Override
    public void getDataSuccess(List<EventPhoto> data) {
        views.getDataSuccess(data);
    }

    @Override
    public void getDataFailed(String message) {
        views.getDataFailed(message);
    }

}
