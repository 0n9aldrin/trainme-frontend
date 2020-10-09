package com.trainme.jerald.frontend.dependencies.response;

import com.trainme.jerald.frontend.dependencies.response.model.AdsModel;

import java.util.List;

public class ResponseGetAds {
    private boolean success;
    private String message;
    private List<AdsModel> data;

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

    public List<AdsModel> getData() {
        return data;
    }

    public void setData(List<AdsModel> data) {
        this.data = data;
    }
}
