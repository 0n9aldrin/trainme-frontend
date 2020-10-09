package com.trainme.jerald.frontend.dependencies.response;

import com.trainme.jerald.frontend.dependencies.response.model.RequesterSparring;

import java.util.List;

public class ResponseAllRequesterSparring {
    private boolean success;
    private String message;
    private List<RequesterSparring> data;

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

    public List<RequesterSparring> getData() {
        return data;
    }

    public void setData(List<RequesterSparring> data) {
        this.data = data;
    }
}
