package com.sena.crud_basic.DTO;

import java.util.List;

public class teamsDTO {
    private String nombre;
    private String descripcion;
    private List<userDTO> miembros;
    
    public teamsDTO() {
    }

    public teamsDTO(String nombre, String descripcion, List<userDTO> miembros) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.miembros = miembros;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<userDTO> getMiembros() {
        return miembros;
    }

    public void setMiembros(List<userDTO> miembros) {
        this.miembros = miembros;
    }
}
