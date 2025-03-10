package com.sena.crud_basic.DTO;

public class customersDTO {
    private String name;

    private String email;

    private String phone;

    private String address;

    public customersDTO(String name, String email, String phone, String address){
        this.name=name;
        this.email=email;
        this.phone=phone;
        this.address=address;
    }

    public String getname(){
        return name;
    }

    public void setname(String name){
        this.name=name;
    }

    public String getemail(){
        return email;
    }

    public void setemail(String email){
        this.email=email;
    }

    public String getphone(){
        return phone;
    }

    public void setphone(String phone){
        this.phone=phone;
    }

    public String getaddress(){
        return address;
    }

    public void setaddress(String address){
        this.address=address;
    }
}
