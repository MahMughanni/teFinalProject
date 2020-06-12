package com.ucas.graduationproject.Model;

public class Item_HolidaysFg {

    int id;
    private String title , description, dateHoliday;

    public Item_HolidaysFg(int id, String title,String date) {
        this.id = id;
        this.title = title;
        this.dateHoliday = date;
    }

    public Item_HolidaysFg() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateHoliday() {
        return dateHoliday;
    }

    public void setDateHoliday(String dateHoliday) {
        this.dateHoliday = dateHoliday;
    }
}
