package com.trainme.jerald.frontend.dependencies.response;

import com.trainme.jerald.frontend.dependencies.models.EventPhoto;

import java.util.List;

public class ResponsePhotoEvent {
    private boolean success;
    private String message;
    private List<EventPhoto> data;

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

    public List<EventPhoto> getData() {
        return data;
    }

    public void setData(List<EventPhoto> data) {
        this.data = data;
    }
}
