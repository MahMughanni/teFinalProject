package com.ucas.graduationproject.Model;

public class Item_NoteFg {

    private String SubTitle, Name, NoteTilte, Description;

    public Item_NoteFg(String subTitle, String name, String noteTilte, String description) {
        SubTitle = subTitle;
        Name = name;
        NoteTilte = noteTilte;
        Description = description;
    }


    public String getSubTitle() {
        return SubTitle;
    }

    public void setSubTitle(String subTitle) {
        SubTitle = subTitle;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNoteTilte() {
        return NoteTilte;
    }

    public void setNoteTilte(String noteTilte) {
        NoteTilte = noteTilte;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

}
