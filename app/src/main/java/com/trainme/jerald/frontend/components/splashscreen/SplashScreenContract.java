package com.trainme.jerald.frontend.components.splashscreen;

/**
 * Created by Marthin on 2/11/2018.
 */

public class SplashScreenContract {
    public interface View {
        void sessionUser(boolean result);
        void getSliderSuccess();
        void getSliderFailed(String message);
    }

    public interface Controller {
        void isLogin();
        void getSlider();
        void getSliderSuccess();
        void getSliderFailed(String message);
    }

    public interface Repository {
        void getSlider();
    }

}
