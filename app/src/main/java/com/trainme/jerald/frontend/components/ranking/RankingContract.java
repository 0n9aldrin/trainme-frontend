package com.trainme.jerald.frontend.components.ranking;

import com.trainme.jerald.frontend.dependencies.models.Ranking;

import java.util.List;

public class RankingContract {
    public interface View {
        void getDataSuccess(List<Ranking> data);

        void getDataFailed(String message);
    }

    public interface Controller {
        void getData();

        void getDataSuccess(List<Ranking> data);

        void getDataFailed(String message);
    }

    public interface Service {
        void getData();
    }
}
