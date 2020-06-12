package com.ucas.graduationproject.Model;

public class AdsData {

    private int id;

    private String title;

    private String image;

    public AdsData(String title, String image) {
        this.title = title;
        this.image = image;
    }

    public AdsData(int id, String title, String image) {
        this.id = id;
        this.title = title;
        this.image = image;
    }

    public AdsData(String image) {
        this.image = image;
    }

    public AdsData() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
