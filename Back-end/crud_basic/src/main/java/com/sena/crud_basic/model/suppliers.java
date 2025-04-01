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
    @Column(name="suppliersId")
        private int suppliersId;
    @Column(name="name")
        private String name;
    @Column(name="contact")
        private String contact;
    @Column(name="phone")
        private String phone;
    
    public suppliers() {
    }

    public suppliers(int suppliersId, String name, String contact, String phone) {
        this.suppliersId = suppliersId;
        this.name = name;
        this.contact = contact;
        this.phone = phone;
    }

    public int getSuppliersId() {
        return suppliersId;
    }

    public void setSuppliersId(int suppliersId) {
        this.suppliersId = suppliersId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}