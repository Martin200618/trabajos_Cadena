package com.sena.crud_basic.model;

import jakarta.persistence.*;

@Entity(name = "Productores")
public class Productores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Productores_id")
    private int Productores_id;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "experiencia_anios", nullable = false)
    private int experienciaAnios;

    public Productores() {}

    public Productores(int Productores_id, String nombre, int experienciaAnios) {
        this.Productores_id = Productores_id;
        this.nombre = nombre;
        this.experienciaAnios = experienciaAnios;
    }

    // Getters y Setters
    public int getProductores_id() {
        return Productores_id;
    }

    public void setProductores_id(int Productores_id) {
        this.Productores_id = Productores_id;
    }

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