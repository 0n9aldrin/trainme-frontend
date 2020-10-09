package com.trainme.jerald.frontend.dependencies.response;

import com.trainme.jerald.frontend.dependencies.response.model.SparringModel;

import java.util.List;

public class ResponseMySparring {
    private boolean success;
    private String message;
    private List<SparringModel> data;

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

    public List<SparringModel> getData() {
        return data;
    }

    public void setData(List<SparringModel> data) {
        this.data = data;
    }
}
