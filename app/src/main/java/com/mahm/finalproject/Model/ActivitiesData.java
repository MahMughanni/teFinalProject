package com.mahm.finalproject.Model;

public class ActivitiesData {

     private int img;
     private String title ;
     private String description ;

    public ActivitiesData(int img, String title, String description) {
        this.img = img;
        this.title = title;
        this.description = description;
    }

    public ActivitiesData(int img) {
        this.img = img;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
