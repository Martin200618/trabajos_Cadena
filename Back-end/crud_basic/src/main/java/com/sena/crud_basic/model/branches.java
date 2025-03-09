package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="branches")
public class branches {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="branch_id")
        private int branch_id;
    @Column(name="name",length=100,nullable=false)
        private String name;
    @Column(name="address",length=225,nullable = false)
        private String address;
    @Column(name="phone",length=20,nullable=false)
        private String phone;

    public branches(int branch_id, String name, String address, String phone){
        this.branch_id=branch_id;
        this.name=name;
        this.address=address;
        this.phone=phone;
    }

    public int getbranch_id(){
        return branch_id;
    }

    public void setbranches_id(int branch_id){
        this.branch_id=branch_id;
    }

    public String getname(){
        return name;
    }

    public void setname (String name){
        this.name=name;
    }

    public String getaddres(){
        return address;
    }

    public void setaddres(String address){
        this.address=address;
    }

    public String getphone(){
        return phone;
    }

    public void setphone(String phone){
        this.phone=phone;
    }
}