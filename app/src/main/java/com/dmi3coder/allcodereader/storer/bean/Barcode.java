package com.dmi3coder.allcodereader.storer.bean;


import com.google.zxing.*;

public class Barcode {

    private Long id;
    private String name;
    private String content;
    private String description;
    private String image;
    private String group;
    private com.google.zxing.BarcodeFormat format;

    public Barcode(Long id, String name, String content, String description, String image, String group, BarcodeFormat format) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.description = description;
        this.image = image;
        this.group = group;
        this.format = format;
    }

    public Barcode(Long id, String name, String content, String description, String image, String group, String  format) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.description = description;
        this.image = image;
        this.group = group;
        this.format = BarcodeFormat.valueOf(format);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public BarcodeFormat getFormat() {
        return format;
    }

    public void setFormat(BarcodeFormat format) {
        this.format = format;
    }

    public void setFormat(String format) {
        this.format = BarcodeFormat.valueOf(format);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Barcode content1 = (Barcode) o;

        return content != null ? content.equals(content1.content) : content1.content == null;

    }

    @Override
    public int hashCode() {
        return content != null ? content.hashCode() : 0;
    }

    public Long getId() {
        return id;
    }
}
