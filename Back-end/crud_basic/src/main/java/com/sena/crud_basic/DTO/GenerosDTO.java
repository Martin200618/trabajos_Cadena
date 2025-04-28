package com.sena.crud_basic.DTO;

public class GenerosDTO {
    private String nombre;

    public GenerosDTO() {}

    public GenerosDTO(String nombre) {
        this.nombre = nombre;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}