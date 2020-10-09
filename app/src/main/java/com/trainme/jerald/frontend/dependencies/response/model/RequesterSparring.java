package com.trainme.jerald.frontend.dependencies.response.model;

import com.google.gson.annotations.SerializedName;

public class RequesterSparring {
    @SerializedName("play_id")
    private String playId;
    private String tile;
    private String notes;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("pp_id")
    private int ppId;
    private String name;
    private String status;
    private String email;
    @SerializedName("phone_number")
    private String phoneNumber;
    private String price;
    private String experience;
    private String image;
    private String gender;
    private String level;
    private String birthdate;
    private String utr;
    private String certificate;

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getUtr() {
        return utr;
    }

    public void setUtr(String utr) {
        this.utr = utr;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public int getPpId() {
        return ppId;
    }

    public void setPpId(int ppId) {
        this.ppId = ppId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPlayId() {
        return playId;
    }

    public void setPlayId(String playId) {
        this.playId = playId;
    }

    public String getTile() {
        return tile;
    }

    public void setTile(String tile) {
        this.tile = tile;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "RequesterSparring{" +
                "playId='" + playId + '\'' +
                ", tile='" + tile + '\'' +
                ", notes='" + notes + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", ppId=" + ppId +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", price='" + price + '\'' +
                ", experience='" + experience + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
