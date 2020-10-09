package com.trainme.jerald.frontend.dependencies.response;

import com.trainme.jerald.frontend.dependencies.models.AllEvents;

import java.util.List;

public class ResponseGetEvent {
    private boolean success;
    private String message;
    private List<AllEvents> data;

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

    public List<AllEvents> getData() {
        return data;
    }

    public void setData(List<AllEvents> data) {
        this.data = data;
    }
}
