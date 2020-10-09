package com.trainme.jerald.frontend.dependencies.response;

import com.trainme.jerald.frontend.dependencies.models.InformasiUmum;

import java.util.List;

/**
 * Created by Marthin on 9/20/2018.
 */

public class ResponseInformasiUmum {
    private boolean success;
    private String message;
    private List<InformasiUmum> data;

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

    public List<InformasiUmum> getData() {
        return data;
    }

    public void setData(List<InformasiUmum> data) {
        this.data = data;
    }
}
