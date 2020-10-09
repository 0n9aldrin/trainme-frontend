package com.trainme.jerald.frontend.components.signup;

import com.trainme.jerald.frontend.dependencies.models.SignupModel;

public class SignupContract {
    public interface View {
        void registerSuccess(String message);

        void registerFailed(String message);
    }

    public interface Controller {
        void registerUser(SignupModel signupModel);

        void registerSuccess(String message);

        void registerFailed(String message);

    }

    public interface Service {
        void registerUser(SignupModel signupModel);
    }
}
