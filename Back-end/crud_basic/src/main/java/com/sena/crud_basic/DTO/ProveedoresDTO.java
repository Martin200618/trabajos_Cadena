package com.sena.crud_basic.DTO;

public class ProveedoresDTO {
    private String nombre;
    private String contacto;
    private String telefono;

    public ProveedoresDTO() {}

    public ProveedoresDTO(String nombre, String contacto, String telefono) {
        this.nombre = nombre;
        this.contacto = contacto;
        this.telefono = telefono;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}