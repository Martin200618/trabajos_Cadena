package com.sena.crud_basic.DTO;

public class ArtistasDTO {
    private String nombre;
    private String pais;

    public ArtistasDTO() {}

    public ArtistasDTO(String nombre, String pais) {
        this.nombre = nombre;
        this.pais = pais;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}