package com.trainme.jerald.frontend.dependencies.models;

import com.google.gson.annotations.SerializedName;

public class RequestJoinSparing {
    @SerializedName("notes")
    private String notes;
    @SerializedName("play_id")
    private int idSparing;
    @SerializedName("partner_id")
    private int partnerId;

    public RequestJoinSparing(String notes, int idSparing, int partnerId) {
        this.notes = notes;
        this.idSparing = idSparing;
        this.partnerId = partnerId;
    }
}
