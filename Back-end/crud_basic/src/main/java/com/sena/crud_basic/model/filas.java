package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="filas")
public class filas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="filasId")
        private int filasId;
    @Column(name="numero")
        private double numero;
    @Column(name="descripcion")
        private String descripcion;
    public filas() {
    }

    public filas(int filasId, double numero, String descripcion) {
        this.filasId = filasId;
        this.numero = numero;
        this.descripcion = descripcion;
    }

    public int getFilasId() {
        return filasId;
    }

    public void setFilasId(int filasId) {
        this.filasId = filasId;
    }

    public double getNumero() {
        return numero;
    }

    public void setNumero(double numero) {
        this.numero = numero;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}