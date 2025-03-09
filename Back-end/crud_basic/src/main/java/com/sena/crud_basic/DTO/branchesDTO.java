package com.sena.crud_basic.DTO;

public class branchesDTO {
    private String name;

    private String address;

    private String phone;

    public branchesDTO(String name, String address, String phone){
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public String getname(){
        return name;
    }

    public void setname(String name){
        this.name=name;
    }

    public String getaddress(){
        return address;
    }

    public void setaddress(String address){
        this.address=address;
    }

    public String getphone(){
        return phone;
    }

    public void setphone(String phone){
        this.phone=phone;
    }
}