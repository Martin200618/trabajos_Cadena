package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="customers")
public class customers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customer_id")
        private int customer_id;
    @Column(name="name",length=100,nullable=false)
        private String name;
    @Column(name="email",length=100,nullable=false)
        private String email;
    @Column(name="phone",length=20,nullable=false)
        private String phone;
    @Column(name="address",length=225,nullable=false)
        private String address;
}