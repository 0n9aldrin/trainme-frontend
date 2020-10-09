package com.trainme.jerald.frontend.dependencies.response.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class AdsModel extends RealmObject {
    @PrimaryKey
    private int id;
    private String image;
    private String description;

    public AdsModel(){
    }

    public AdsModel(int id, String image, String description) {
        this.id = id;
        this.image = image;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
