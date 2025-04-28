package com.sena.crud_basic.model;

import jakarta.persistence.*;

@Entity(name = "Empleados")
public class Empleados {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Empleados_id")
    private int Empleados_id;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "puesto", length = 50, nullable = false)
    private String puesto;

    @Column(name = "email", length = 100, nullable = false)
    private String email;

    public Empleados() {}

    public Empleados(int Empleados_id, String nombre, String puesto, String email) {
        this.Empleados_id = Empleados_id;
        this.nombre = nombre;
        this.puesto = puesto;
        this.email = email;
    }

    // Getters y Setters
    public int getEmpleados_id() {
        return Empleados_id;
    }

    public void setEmpleados_id(int Empleados_id) {
        this.Empleados_id = Empleados_id;
    }

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