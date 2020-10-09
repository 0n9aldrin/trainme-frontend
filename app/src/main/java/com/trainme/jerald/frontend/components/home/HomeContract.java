package com.trainme.jerald.frontend.components.home;

import com.trainme.jerald.frontend.dependencies.response.model.AdsModel;
import com.trainme.jerald.frontend.dependencies.response.model.User;

import java.util.List;

public class HomeContract {
    public interface View {
        void getAdsSuccess(List<AdsModel> data);
        void getAdsFailed(String message);
        void signOutSuccess();
    }
    public interface Controller {
        User getUser();
        void getAds();
        void signOut();
    }
}
