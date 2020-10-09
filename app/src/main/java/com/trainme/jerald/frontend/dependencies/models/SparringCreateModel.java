package com.trainme.jerald.frontend.dependencies.models;

import com.google.gson.annotations.SerializedName;

public class SparringCreateModel {
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
    private String desription;
    @SerializedName("address")
    private String address;
    @SerializedName("level")
    private String level;
    @SerializedName("play_type")
    private int playType;

    public SparringCreateModel(int userId, String title, String sparingDate, String expiredDate,
                               String time, String desription, String address, int playType, String level) {
        this.userId = userId;
        this.title = title;
        this.sparingDate = sparingDate;
        this.expiredDate = expiredDate;
        this.time = time;
        this.desription = desription;
        this.address = address;
        this.level = level;
        this.playType = playType;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
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

    public String getDesription() {
        return desription;
    }

    public void setDesription(String desription) {
        this.desription = desription;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPlayType() {
        return playType;
    }

    public void setPlayType(int playType) {
        this.playType = playType;
    }

    @Override
    public String toString() {
        return "SparringCreateModel{" +
                "userId=" + userId +
                ", title='" + title + '\'' +
                ", sparingDate='" + sparingDate + '\'' +
                ", expiredDate='" + expiredDate + '\'' +
                ", time='" + time + '\'' +
                ", desription='" + desription + '\'' +
                ", address='" + address + '\'' +
                ", level='" + level + '\'' +
                ", playType=" + playType +
                '}';
    }
}
