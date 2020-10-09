package com.trainme.jerald.frontend.dependencies.models;

import com.google.gson.annotations.SerializedName;

public class SignupModel {
    @SerializedName("email")
    private String email;
    @SerializedName("name")
    private String name;
    @SerializedName("phone_number")
    private String phoneNumber;
    @SerializedName("password")
    private String password;
    @SerializedName("role")
    private int role;
    @SerializedName("address")
    private String address;
    @SerializedName("price")
    private String price;
    @SerializedName("experience")
    private String experience;
    @SerializedName("utr")
    private String utr;
    @SerializedName("gender")
    private String gender;
    @SerializedName("level")
    private String level;
    @SerializedName("birthdate")
    private String birthdate;


    public SignupModel() {
    }

    public SignupModel(String email, String name, String phoneNumber, String password, int role, String address,
                       String price, String experience, String birthdate, String gender,
                       String level, String utr) {
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.role = role;
        this.address = address;
        this.price = price;
        this.experience = experience;
        this.birthdate = birthdate;
        this.gender = gender;
        this.level = level;
        this.utr = utr;
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

    public String getUtr() {
        return utr;
    }

    public void setUtr(String utr) {
        this.utr = utr;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "SignupModel{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", address='" + address + '\'' +
                ", price='" + price + '\'' +
                ", experience='" + experience + '\'' +
                ", utr='" + utr + '\'' +
                ", gender='" + gender + '\'' +
                ", level='" + level + '\'' +
                ", birthdate='" + birthdate + '\'' +
                '}';
    }
}
