package com.trainme.jerald.frontend.dependencies.response;

import com.trainme.jerald.frontend.dependencies.models.KebijakanPrivasi;

import java.util.List;

/**
 * Created by Marthin on 9/20/2018.
 */

public class ResponseKebijakanPrivasi {
    private boolean success;
    private String message;
    private List<KebijakanPrivasi> data;

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

    public List<KebijakanPrivasi> getData() {
        return data;
    }

    public void setData(List<KebijakanPrivasi> data) {
        this.data = data;
    }
}
