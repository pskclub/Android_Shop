package com.mhalong.shophomework.model;

/**
 * Created by passa on 10/11/2559.
 */

public class ProductListItem {
    private String name;
    private String category;
    private String description;
    private int image;
    private double price;

    public ProductListItem() {

    }

    public ProductListItem(String name, String category, String description, int image, double price) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.image = image;
        this.price = price;
    }


  /*  public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }*/

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

    public int getIamge() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
