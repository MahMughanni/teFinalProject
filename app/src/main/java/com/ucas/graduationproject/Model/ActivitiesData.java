package com.ucas.graduationproject.Model;

public class ActivitiesData {
    private int activitytId;
    private String title;
    private String description;
    private String img;
    private int type;


    public ActivitiesData(String title, String description, String img) {
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
