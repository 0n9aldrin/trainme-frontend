package com.trainme.jerald.frontend.components.informasi;

import android.support.annotation.NonNull;

import com.trainme.jerald.frontend.dependencies.component.AppComponent;
import com.trainme.jerald.frontend.dependencies.models.InformasiUmum;
import com.trainme.jerald.frontend.dependencies.webservices.InformasiUmumService;

import java.util.List;
import javax.inject.Inject;
import io.realm.Realm;

/**
 * Created by Marthin on 9/20/2018.
 */

public class InformasiUmumController implements InformasiUmumContract.Controller {

    @Inject
    InformasiUmumService mService;

    private InformasiUmumContract.View views;

    public InformasiUmumController(@NonNull AppComponent appComponent) {
        appComponent.inject(this);
    }

    public void setView(InformasiUmumContract.View view){
        views = view;
    }

    @Override
    public void getData() {
        mService.instanceClass(this);
        mService.getData();
    }

    @Override
    public void getDataSuccess(List<InformasiUmum> data) {
        views.getDataSuccess(data);
    }

    @Override
    public void getDataFailed(String message) {
        views.getDataFailed(message);
    }
}