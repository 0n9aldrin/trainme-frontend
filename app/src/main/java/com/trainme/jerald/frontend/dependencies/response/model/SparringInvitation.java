package com.trainme.jerald.frontend.dependencies.response.model;

import com.google.gson.annotations.SerializedName;

public class SparringInvitation {
    @SerializedName("play_id")
    private int playId;
    @SerializedName("user_id")
    private int userId;
    @SerializedName("name")
    private String name;
    @SerializedName("phone_number")
    private String phoneNumber;
    @SerializedName("sparing_date")
    private String sparingDate;
    @SerializedName("title")
    private String title;
    @SerializedName("expired_date")
    private String expiredDate;
    @SerializedName("time")
    private String time;
    @SerializedName("desription")
    private String description;
    @SerializedName("address")
    private String address;
    @SerializedName("level")
    private String level;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getPlayId() {
        return playId;
    }

    public void setPlayId(int playId) {
        this.playId = playId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    @Override
    public String toString() {
        return "SparringInvitation{" +
                "playId=" + playId +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", sparingDate='" + sparingDate + '\'' +
                ", title='" + title + '\'' +
                ", expiredDate='" + expiredDate + '\'' +
                ", time='" + time + '\'' +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
