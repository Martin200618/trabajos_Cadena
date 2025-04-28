package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "Artistas")
public class Artistas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Artistas_id")
    private int Artistas_id;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "pais", length = 50, nullable = false)
    private String pais;

    // Constructor vacío
    public Artistas() {}

    // Constructor con parámetros
    public Artistas(int Artistas_id, String nombre, String pais) {
        this.Artistas_id = Artistas_id;
        this.nombre = nombre;
        this.pais = pais;
    }

    // Getters y Setters
    public int getArtistas_id() {
        return Artistas_id;
    }

    public void setArtistas_id(int Artistas_id) {
        this.Artistas_id = Artistas_id;
    }

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