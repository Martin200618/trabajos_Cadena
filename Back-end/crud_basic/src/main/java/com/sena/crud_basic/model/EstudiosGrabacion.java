package com.sena.crud_basic.model;

import jakarta.persistence.*;

@Entity(name = "EstudiosGrabacion")
public class EstudiosGrabacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EstudiosGrabacion_id")
    private int EstudiosGrabacion_id;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "ciudad", length = 100, nullable = false)
    private String ciudad;

    @Column(name = "pais", length = 50, nullable = false)
    private String pais;

    public EstudiosGrabacion() {}

    public EstudiosGrabacion(int EstudiosGrabacion_id, String nombre, String ciudad, String pais) {
        this.EstudiosGrabacion_id = EstudiosGrabacion_id;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.pais = pais;
    }

    // Getters y Setters
    public int getEstudiosGrabacion_id() {
        return EstudiosGrabacion_id;
    }

    public void setEstudiosGrabacion_id(int EstudiosGrabacion_id) {
        this.EstudiosGrabacion_id = EstudiosGrabacion_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}