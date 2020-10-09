package com.trainme.jerald.frontend.dependencies.response;

import com.trainme.jerald.frontend.dependencies.models.Profile;

import java.util.List;

public class ResponseGetProfile {
    private boolean success;
    private String message;
    private List<Profile> data;

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

    public List<Profile> getData() {
        return data;
    }

    public void setData(List<Profile> data) {
        this.data = data;
    }
}
