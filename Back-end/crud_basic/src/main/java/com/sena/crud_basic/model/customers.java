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

    public customers (int customer_id, String name, String email, String phone, String address){
        this.customer_id = customer_id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public int getcustomer_id(){
        return customer_id;
    }

    public void setcustomer_id(int customer_id){
        this.customer_id = customer_id;
    }

    public String getname(){
        return name;
    }

    public void setname(String name){
        this.name = name;
    }

    public String getemail(){
        return email;
    }

    public void setemail(String email){
        this.email = email;
    }

    public String getphone(){
        return phone;
    }

    public void setphone(String phone){
        this.phone = phone;
    }

    public String getaddress(){
        return address;
    }

    public void setaddress(String address){
        this.address = address;
    }
}