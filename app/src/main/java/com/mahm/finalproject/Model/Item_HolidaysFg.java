package com.mahm.finalproject.Model;

public class Item_HolidaysFg {

    private String title , description ;

    public Item_HolidaysFg(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Item_HolidaysFg() {
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
