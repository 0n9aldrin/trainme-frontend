package com.trainme.jerald.frontend.dependencies.response;

import com.trainme.jerald.frontend.dependencies.response.model.SparringInvitation;

import java.util.List;

public class ResponseGetAllSparing {
    private boolean success;
    private String message;
    private List<SparringInvitation> data;

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

    public List<SparringInvitation> getData() {
        return data;
    }

    public void setData(List<SparringInvitation> data) {
        this.data = data;
    }
}
