package com.trainme.jerald.frontend.contracts;

import java.util.List;

public class DataContract {
    public interface View<U> {
        void resultDataExist(List<U> allData);
        void resultFailed(String message);
    }

    public interface Controller<U> {
        void getData();
        void resultDataExist(List<U> allData);
        void resultFailed(String message);
    }

    public interface Service<U> {
        void getData();
    }
}
