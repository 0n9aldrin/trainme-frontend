package com.trainme.jerald.frontend.components.signin;

import com.trainme.jerald.frontend.dependencies.models.SigninModel;

public class SigninContract {
    public interface View {
        void signinSuccess(String message);

        void signinFailed(String message);
    }

    public interface Controller {
        void signin(SigninModel signinModel);

        void signinSuccess(String message);

        void signinFailed(String message);

    }

    public interface Service {
        void signin(SigninModel signinModel);
    }
}
