package com.sena.crud_basic.DTO;

public class EmpleadosDTO {
    private String nombre;
    private String puesto;
    private String email;

    public EmpleadosDTO() {}

    public EmpleadosDTO(String nombre, String puesto, String email) {
        this.nombre = nombre;
        this.puesto = puesto;
        this.email = email;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}