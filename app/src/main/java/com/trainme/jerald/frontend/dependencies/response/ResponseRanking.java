package com.trainme.jerald.frontend.dependencies.response;

import com.trainme.jerald.frontend.dependencies.models.Ranking;

import java.util.List;

public class ResponseRanking {
    private boolean success;
    private String message;
    private List<Ranking> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Ranking> getData() {
        return data;
    }

    public void setData(List<Ranking> data) {
        this.data = data;
    }
}
