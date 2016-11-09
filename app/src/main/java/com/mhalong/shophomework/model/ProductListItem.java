package com.mhalong.shophomework.model;

/**
 * Created by passa on 10/11/2559.
 */

public class ProductListItem {
    private int id;
    private String name;
    private String category;
    private String description;
    private String image;
    private double price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIamge() {
        return iamge;
    }

    public void setImage(String iamge) {
        this.iamge = iamge;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
