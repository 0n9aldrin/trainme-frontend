package com.trainme.jerald.frontend.dependencies.models;

public class Ranking {
    private int id;
    private int genderid;
    private String age;
    private String link;

    public Ranking() {
    }

    public Ranking(int id, int genderid, String age, String link) {
        this.id = id;
        this.genderid = genderid;
        this.age = age;
        this.link = link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGenderid() {
        return genderid;
    }

    public void setGenderid(int genderid) {
        this.genderid = genderid;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
