package com.trainme.jerald.frontend.components.informasi;

import com.trainme.jerald.frontend.dependencies.models.InformasiUmum;

import java.util.List;

/**
 * Created by Marthin on 9/20/2018.
 */

public class InformasiUmumContract {
    public interface View {
        void getDataSuccess(List<InformasiUmum> data);
        void getDataFailed(String message);
    }

    public interface Controller {
        void getData();
        void getDataSuccess(List<InformasiUmum> data);
        void getDataFailed(String message);
    }

    public interface Repository{
        void getData();
    }
}
