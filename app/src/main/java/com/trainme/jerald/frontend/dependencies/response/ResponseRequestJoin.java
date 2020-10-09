package com.trainme.jerald.frontend.dependencies.response;

import com.trainme.jerald.frontend.dependencies.models.RequestJoinSparing;

public class ResponseRequestJoin {
    private boolean success;
    private String message;
    private RequestJoinSparing data;

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

    public RequestJoinSparing getData() {
        return data;
    }

    public void setData(RequestJoinSparing data) {
        this.data = data;
    }
}
