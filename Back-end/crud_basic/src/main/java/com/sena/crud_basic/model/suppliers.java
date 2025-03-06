package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="suppliers")
public class suppliers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="supplier_id")
        private int supplier_id;
    @Column(name="name",length=100,nullable=false)
        private String name;
    @Column(name="contact",nullable = false,length = 100)
        private String contact;
    @Column(name="address",nullable = false,length = 100)
        private String address;
    public suppliers(int supplier_id, String name, String contact) {
        this.supplier_id = supplier_id;
        this.name = name;
        this.contact = contact;
    }

    public int getsupplier_id(){
        return supplier_id;
    }

    public void setsupplier_id(int supplier_id){
        this.supplier_id=supplier_id;
    }

    public String getname(){
        return name;
    }

    public void setname(String name){
        this.name=name;
    }

    public String getcontact(){
        return contact;
    }

    public void setcontact(String contact){
        this.contact=contact;
    }

    public String getaddress(){
        return address;
    }

    public void setaddress(String address){
        this.address=address;
    }
    
}