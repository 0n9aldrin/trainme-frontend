package com.trainme.jerald.frontend.dependencies.response.model;

import com.google.gson.annotations.SerializedName;

public class MyRequestSparringStatus {
    private String title;
    @SerializedName("pp_id")
    private int ppId;
    private String notes;
    @SerializedName("created_at")
    private String createdAt;
    private String status;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPpId() {
        return ppId;
    }

    public void setPpId(int ppId) {
        this.ppId = ppId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
