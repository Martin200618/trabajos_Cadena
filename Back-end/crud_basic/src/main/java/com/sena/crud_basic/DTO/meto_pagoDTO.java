package com.sena.crud_basic.DTO;

public class meto_pagoDTO {
    private String metodo;

    public meto_pagoDTO(String metodo){
        this.metodo=metodo;
    }

    public String getmetodo(){
        return metodo;
    }
    public void setmetodo(String metodo){
        this.metodo=metodo;
    }
}
