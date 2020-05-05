package com.mahm.finalproject.Model;

public class ActivitiesData {
    private int activitytId;
    private String title;
    private String description;
    private String img;


    public ActivitiesData(int activitytId, String title, String description, String img) {
        this.activitytId = activitytId;
        this.title = title;
        this.description = description;
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getActivitytId() {
        return activitytId;
    }

    public void setActivitytId(int activitytId) {
        this.activitytId = activitytId;
    }

    public ActivitiesData() {
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
