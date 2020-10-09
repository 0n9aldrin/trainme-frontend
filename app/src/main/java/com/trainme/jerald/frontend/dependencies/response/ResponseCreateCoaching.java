package com.trainme.jerald.frontend.dependencies.response;

import com.trainme.jerald.frontend.dependencies.response.model.SparringInvitation;

public class ResponseCreateCoaching {
    private boolean success;
    private String message;
    private SparringInvitation data;

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

    public SparringInvitation getData() {
        return data;
    }

    public void setData(SparringInvitation data) {
        this.data = data;
    }
}
