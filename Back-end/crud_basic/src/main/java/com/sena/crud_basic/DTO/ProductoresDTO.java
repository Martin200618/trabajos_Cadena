package com.sena.crud_basic.DTO;

public class ProductoresDTO {
    private String nombre;
    private int experienciaAnios;

    public ProductoresDTO() {}

    public ProductoresDTO(String nombre, int experienciaAnios) {
        this.nombre = nombre;
        this.experienciaAnios = experienciaAnios;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getExperienciaAnios() {
        return experienciaAnios;
    }

    public void setExperienciaAnios(int experienciaAnios) {
        this.experienciaAnios = experienciaAnios;
    }
}