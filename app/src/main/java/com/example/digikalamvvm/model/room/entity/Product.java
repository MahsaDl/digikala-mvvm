package com.example.digikalamvvm.model.room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Product {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo
    private String imageUrl;

    @ColumnInfo
    private String name;

    @ColumnInfo
    private String price;

    @ColumnInfo
    private String anbar;

    @ColumnInfo
    private String model;

    public Product() {
    }

    @Ignore
    public Product(int id,String imageUrl, String name, String price, String anbar, String model) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.price = price;
        this.anbar = anbar;
        this.model = model;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAnbar() {
        return anbar;
    }

    public void setAnbar(String anbar) {
        this.anbar = anbar;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
