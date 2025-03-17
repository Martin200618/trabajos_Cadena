package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name="products")
public class products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
        private int product_id;
    @Column(name="name",length = 100,nullable = false)
        private String name;
    @Column(name="description",length = 225)
        private String description;
    @Column(name="price")
        private double price;
    @Column(name="stock")
        private double stock;
    @ManyToOne
    @JoinColumn(name="categorie_id")
        private categories categorie_id;
    public products() {
    }
    public products(int product_id, String name, String description, double price, double stock, categories categorie_id) {
        this.product_id = product_id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.categorie_id = categorie_id;
    }
    public int getProduct_id() {
        return product_id;
    }
    public void setProduct_id(int product_id) {
        this.product_id = product_id;
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
    public double getStock() {
        return stock;
    }
    public void setStock(double stock) {
        this.stock = stock;
    }
    public categories getCategorie_id() {
        return categorie_id;
    }
    public void setCategorie_id(categories categorie_id) {
        this.categorie_id = categorie_id;
    }
}
