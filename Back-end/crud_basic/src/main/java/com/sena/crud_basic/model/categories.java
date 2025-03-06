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
    @Column(name="category_id")
        private int category_id;
    @Column(name="name",length=100,nullable=false)
        private String name;
    public categories(int category_id, String name) {
        this.category_id = category_id;
        this.name = name;
    }

    public int getcategory_id(){
        return category_id;
    }

    public void setcategory_id(int category_id){
        this.category_id = category_id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}