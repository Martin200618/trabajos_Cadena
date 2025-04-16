package com.sena.crud_basic.DTO;

import java.time.LocalDateTime;

import com.sena.crud_basic.model.proyects;
import com.sena.crud_basic.model.user;

public class commentDTO {
    private proyects proyectId;
    private user user_id;
    private String texto;
    private LocalDateTime fecha_creacion;
    
    public commentDTO() {
    }

    public commentDTO(proyects proyectId, user user_id, String texto, LocalDateTime fecha_creacion) {
        this.proyectId = proyectId;
        this.user_id = user_id;
        this.texto = texto;
        this.fecha_creacion = fecha_creacion;
    }

    public proyects getProyectId() {
        return proyectId;
    }

    public void setProyectId(proyects proyectId) {
        this.proyectId = proyectId;
    }

    public user getUser_id() {
        return user_id;
    }

    public void setUser_id(user user_id) {
        this.user_id = user_id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public LocalDateTime getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(LocalDateTime fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }
}
