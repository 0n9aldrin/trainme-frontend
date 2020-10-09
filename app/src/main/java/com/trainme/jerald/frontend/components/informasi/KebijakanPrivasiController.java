package com.trainme.jerald.frontend.components.informasi;

import android.support.annotation.NonNull;

import com.trainme.jerald.frontend.dependencies.component.AppComponent;
import com.trainme.jerald.frontend.dependencies.models.KebijakanPrivasi;
import com.trainme.jerald.frontend.dependencies.webservices.KebijakanPrivasiService;

import java.util.List;
import javax.inject.Inject;

/**
 * Created by Marthin on 9/20/2018.
 */

public class KebijakanPrivasiController implements KebijakanPrivasiContract.Controller {

    @Inject
    KebijakanPrivasiService mService;

    private KebijakanPrivasiContract.View views;

    public KebijakanPrivasiController(@NonNull AppComponent appComponent) {
        appComponent.inject(this);
    }

    public void setView(KebijakanPrivasiContract.View view){
        views = view;
    }

    @Override
    public void getData() {
        mService.instanceClass(this);
        mService.getData();
    }

    @Override
    public void getDataSuccess(List<KebijakanPrivasi> data) {
        views.getDataSuccess(data);
    }

    @Override
    public void getDataFailed(String message) {
        views.getDataFailed(message);
    }
}