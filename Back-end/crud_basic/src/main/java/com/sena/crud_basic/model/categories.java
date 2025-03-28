package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="categories")
public class categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="categories_id")
        private int categories_id;
    @Column(name="name",length = 50)
        private String name;
    public categories() {
    }
    public categories(int categories_id, String name) {
        this.categories_id = categories_id;
        this.name = name;
    }
    public int getCategorie_id() {
        return categories_id;
    }
    public void setCategorie_id(int categories_id) {
        this.categories_id = categories_id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
