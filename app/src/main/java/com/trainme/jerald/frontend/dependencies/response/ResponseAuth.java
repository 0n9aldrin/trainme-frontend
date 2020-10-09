package com.trainme.jerald.frontend.dependencies.response;

import com.trainme.jerald.frontend.dependencies.response.model.User;

public class ResponseAuth {
    private boolean success;
    private String message;
    private User data;

    public ResponseAuth(boolean success, String message, User data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }

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
}
