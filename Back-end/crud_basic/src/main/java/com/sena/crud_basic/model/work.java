package com.sena.crud_basic.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name="work")
public class work {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="work_id")
    private int work_id;

    @ManyToOne
    @JoinColumn(name="proyecto_id", nullable = false)
    private proyects proyectId;

    @Column(name="titulo", length = 255, nullable = false)
    private String titulo;

    @Column(name="stateWork", length = 50, nullable = false)
    private stateWork stateWork; // Pendiente, En progreso, Completada

    @Column(name="fecha_creacion", nullable = false)
    private LocalDateTime fecha_creacion;

    public work() {}

    public work(int work_id, proyects proyectId, String titulo, stateWork stateWork, LocalDateTime fecha_creacion) {
        this.work_id = work_id;
        this.proyectId = proyectId;
        this.titulo = titulo;
        this.stateWork = stateWork;
        this.fecha_creacion = fecha_creacion;
    }

    public int getWork_id() {
        return work_id;
    }

    public void setWork_id(int work_id) {
        this.work_id = work_id;
    }

    public proyects getProyectId() {
        return proyectId;
    }

    public void setProyectId(proyects proyectId) {
        this.proyectId = proyectId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public stateWork getStateWork() {
        return stateWork;
    }

    public void setStateWork(stateWork stateWork) {
        this.stateWork = stateWork;
    }

    public LocalDateTime getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(LocalDateTime fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }
}