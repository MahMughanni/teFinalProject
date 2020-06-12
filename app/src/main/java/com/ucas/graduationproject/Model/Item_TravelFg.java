package com.ucas.graduationproject.Model;

public class Item_TravelFg {
    private String img;
    private String title ;

    public Item_TravelFg(String img, String title) {
        this.img = img;
        this.title = title;
    }

    public Item_TravelFg() {
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
