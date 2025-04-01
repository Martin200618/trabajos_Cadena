package com.sena.crud_basic.DTO;

public class suppliersDTO {
    private String name;
    private String contact;
    private String phone;
    
    public suppliersDTO() {
    }

    public suppliersDTO(String name, String contact, String phone) {
        this.name = name;
        this.contact = contact;
        this.phone = phone;
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
