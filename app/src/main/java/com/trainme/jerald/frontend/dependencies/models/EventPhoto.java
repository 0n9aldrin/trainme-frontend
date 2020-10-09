package com.trainme.jerald.frontend.dependencies.models;

import com.google.gson.annotations.SerializedName;

public class EventPhoto {
    private int id;
    @SerializedName("event_id")
    private int eventId;
    private String photo;
    private String description;

    public EventPhoto() {
    }

    public EventPhoto(int id, int eventId, String photo, String description) {
        this.id = id;
        this.eventId = eventId;
        this.photo = photo;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
