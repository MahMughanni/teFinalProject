package com.mahm.finalproject.Model;

public class Item_NoteFg {

    private String SubTitle ,Name , NoteTilte , Description ;
    private int Img_Desc ;


    public Item_NoteFg(String subTitle, String name, String noteTilte, String description, int img_Desc) {
        SubTitle = subTitle;
        Name = name;
        NoteTilte = noteTilte;
        Description = description;
        Img_Desc = img_Desc;
    }

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

    public int getImg_Desc() {
        return Img_Desc;
    }

    public void setImg_Desc(int img_Desc) {
        Img_Desc = img_Desc;
    }
}
