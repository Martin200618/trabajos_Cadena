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
    @Column(name="categorie_id")
        private int categorie_id;
    @Column(name="name",length = 50)
        private String name;
    public categories() {
    }
    public categories(int categorie_id, String name) {
        this.categorie_id = categorie_id;
        this.name = name;
    }
    public int getCategorie_id() {
        return categorie_id;
    }
    public void setCategorie_id(int categorie_id) {
        this.categorie_id = categorie_id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
