package com.sena.crud_basic.DTO;

public class categoriasDTO {
    private String nombre;

    public categoriasDTO(String nombre){
        this.nombre=nombre;
    }

    public String getnombre(){
        return nombre;
    }

    public void setnombre(String nombre){
        this.nombre=nombre;
    }
}
