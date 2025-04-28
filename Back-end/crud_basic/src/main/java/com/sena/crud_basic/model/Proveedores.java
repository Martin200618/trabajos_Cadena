package com.sena.crud_basic.model;

import jakarta.persistence.*;

@Entity(name = "Proveedores")
public class Proveedores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Proveedores_id")
    private int Proveedores_id;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "contacto", length = 100)
    private String contacto;

    @Column(name = "telefono", length = 20)
    private String telefono;

    public Proveedores() {}

    public Proveedores(int Proveedores_id, String nombre, String contacto, String telefono) {
        this.Proveedores_id = Proveedores_id;
        this.nombre = nombre;
        this.contacto = contacto;
        this.telefono = telefono;
    }

    // Getters y Setters
    public int getProveedores_id() {
        return Proveedores_id;
    }

    public void setProveedores_id(int Proveedores_id) {
        this.Proveedores_id = Proveedores_id;
    }

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