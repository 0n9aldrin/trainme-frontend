package com.trainme.jerald.frontend.dependencies.models;

import com.google.gson.annotations.SerializedName;

public class CoachingCreateModel {
    @SerializedName("user_id")
    private int userId;
    @SerializedName("title")
    private String title;
    @SerializedName("sparing_date")
    private String sparingDate;
    @SerializedName("expired_date")
    private String expiredDate;
    @SerializedName("time")
    private String time;
    @SerializedName("desription")
    private String description;
    @SerializedName("address")
    private String address;

    public CoachingCreateModel(int userId, String title, String sparingDate, String expiredDate,
                               String time, String description, String address) {
        this.userId = userId;
        this.title = title;
        this.sparingDate = sparingDate;
        this.expiredDate = expiredDate;
        this.time = time;
        this.description = description;
        this.address = address;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSparingDate() {
        return sparingDate;
    }

    public void setSparingDate(String sparingDate) {
        this.sparingDate = sparingDate;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "CoachingCreateModel{" +
                "userId=" + userId +
                ", title='" + title + '\'' +
                ", sparingDate='" + sparingDate + '\'' +
                ", expiredDate='" + expiredDate + '\'' +
                ", time='" + time + '\'' +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
