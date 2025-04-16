package com.sena.crud_basic.DTO;

import java.time.LocalDateTime;

import com.sena.crud_basic.model.proyects;
import com.sena.crud_basic.model.stateWork;

public class workDTO {
    private proyects proyect_id;
    private String titulo;
    private stateWork stateWork;
    private LocalDateTime fecha_creacion;
    
    public workDTO() {
    }

    public workDTO(proyects proyect_id, String titulo, com.sena.crud_basic.model.stateWork stateWork, LocalDateTime fecha_creacion) {
        this.proyect_id = proyect_id;
        this.titulo = titulo;
        this.stateWork = stateWork;
        this.fecha_creacion = fecha_creacion;
    }

    public proyects getProyect_id() {
        return proyect_id;
    }

    public void setProyect_id(proyects proyect_id) {
        this.proyect_id = proyect_id;
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