package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity(name="proyects")
public class proyects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="proyectId")
        private int proyectId;
    @Column(name="nombre")
        private String nombre;
    @Lob
    @Column(name="imagen")
        private String imagenBase64;
    @Column(name="descripcion")
        private String descripcion;

    public proyects() {
    }

    public proyects(int proyectId, String nombre, String imagenBase64, String descripcion) {
        this.proyectId = proyectId;
        this.nombre = nombre;
        this.imagenBase64 = imagenBase64;
        this.descripcion = descripcion;
    }

    public int getProyectId() {
        return proyectId;
    }

    public void setProyectId(int proyectId) {
        this.proyectId = proyectId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagenBase64() {
        return imagenBase64;
    }

    public void setImagenBase64(String imagenBase64) {
        this.imagenBase64 = imagenBase64;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}