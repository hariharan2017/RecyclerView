package com.cringe.recyclerview;


public class ModelClass {
    String desc,image,name;

    public ModelClass() {
    }

    public ModelClass(String desc, String image, String name) {
        this.desc = desc;
        this.image = image;
        this.name = name;
    }

    public String getDesc() { return desc; }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
