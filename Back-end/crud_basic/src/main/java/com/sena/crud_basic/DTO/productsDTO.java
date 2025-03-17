package com.sena.crud_basic.DTO;

import com.sena.crud_basic.model.categories;

public class productsDTO {
    private int product_id;
    private categories categorie_id;
    private String name;
    private String description;
    private double price;
    private double stock;

    public productsDTO(int product_id, categories categorie_id, String name, String description, double price, double stock) {
        this.product_id=product_id;
        this.categorie_id=categorie_id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }
    
    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public categories getCategorie_id() {
        return categorie_id;
    }

    public void setCategorie_id(categories categorie_id) {
        this.categorie_id = categorie_id;
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