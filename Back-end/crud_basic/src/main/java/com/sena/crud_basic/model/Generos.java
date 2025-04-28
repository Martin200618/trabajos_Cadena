package com.sena.crud_basic.model;

import jakarta.persistence.*;

@Entity(name = "Generos")
public class Generos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Generos_id")
    private int Generos_id;

    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    public Generos() {}

    public Generos(int Generos_id, String nombre) {
        this.Generos_id = Generos_id;
        this.nombre = nombre;
    }

    // Getters y Setters
    public int getGeneros_id() {
        return Generos_id;
    }

    public void setGeneros_id(int Generos_id) {
        this.Generos_id = Generos_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}