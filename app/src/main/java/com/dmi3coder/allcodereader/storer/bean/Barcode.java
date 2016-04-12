package com.dmi3coder.allcodereader.storer.bean;


import com.orm.SugarRecord;
import com.orm.dsl.Table;


@Table
public class Barcode extends SugarRecord {

    private Long id;
    private String name;
    private String barcode;
    private String description;
    private String image;
    private String group;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
