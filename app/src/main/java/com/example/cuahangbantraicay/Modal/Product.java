package com.example.cuahangbantraicay.Modal;

import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private String name;

    private String image;
    private float price_in;
    private float price_sell;
    private  String content;
    private int category_id;
    private int discount;
    private int quantity;
    private int quantity_sold;
    private boolean status;

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPrice_in() {
        return price_in;
    }

    public void setPrice_in(float price_in) {
        this.price_in = price_in;
    }

    public float getPrice_sell() {
        return price_sell;
    }

    public void setPrice_sell(float price_sell) {
        this.price_sell = price_sell;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity_sold() {
        return quantity_sold;
    }

    public void setQuantity_sold(int quantity_sold) {
        this.quantity_sold = quantity_sold;
    }

    public Product(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getResourceId() {
        return id;
    }

    public void setResourceId(int resourceId) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
