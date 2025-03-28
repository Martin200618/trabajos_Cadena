package com.sena.crud_basic.DTO;

import com.sena.crud_basic.model.categories;

public class productsDTO {
    private String name;
    private String description;
    private double price;
    private double stock;
    private categories categories_id;

    public productsDTO(int product_id, categories categories_id, String name, String description, double price, double stock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.categories_id = categories_id;
    }

    public productsDTO() {
    }
    public categories getCategories_id() {
        return categories_id;
    }

    public void setCategories_id(categories categories_id) {
        this.categories_id = categories_id;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}