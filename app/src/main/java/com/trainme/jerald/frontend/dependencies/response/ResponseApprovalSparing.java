package com.trainme.jerald.frontend.dependencies.response;

import com.trainme.jerald.frontend.dependencies.models.ApprovalSparing;

public class ResponseApprovalSparing {
    private boolean success;
    private String message;
    private ApprovalSparing data;

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

    public ApprovalSparing getData() {
        return data;
    }

    public void setData(ApprovalSparing data) {
        this.data = data;
    }
}
