package com.sena.crud_basic.DTO;

public class categoriesDTO {
    private String name;
    
    public categoriesDTO(String name){
        this.name=name;
    }
    public String getName() {
        return name;
    }

    public void setname(String name){
        this.name=name;
    }
}
