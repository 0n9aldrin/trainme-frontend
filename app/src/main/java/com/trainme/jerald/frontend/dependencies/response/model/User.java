package com.trainme.jerald.frontend.dependencies.response.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class User extends RealmObject {
    @SerializedName("user_id")
    private int id;
    @SerializedName("username")
    private String username;
    @SerializedName("email")
    private String email;
    @SerializedName("role")
    private String role;
    @SerializedName("phone_number")
    private String phoneNumber;
    @SerializedName("address")
    private String address;

    public User() {
    }

    public User(int id, String username, String email, String role, String phoneNumber, String address) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
