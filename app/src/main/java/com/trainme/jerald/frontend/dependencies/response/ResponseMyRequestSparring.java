package com.trainme.jerald.frontend.dependencies.response;

import com.trainme.jerald.frontend.dependencies.response.model.MyRequestSparringStatus;

import java.util.List;

public class ResponseMyRequestSparring {
    private boolean success;
    private String message;
    private List<MyRequestSparringStatus> data;

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

    public List<MyRequestSparringStatus> getData() {
        return data;
    }

    public void setData(List<MyRequestSparringStatus> data) {
        this.data = data;
    }
}
