package com.sena.crud_basic.DTO;

import java.time.LocalDateTime;
import com.sena.crud_basic.model.user;

public class ActivityDTO {
    private user user_id;
    private String accion;
    private LocalDateTime fecha;

    public ActivityDTO() {
    }

    public ActivityDTO(user user_id, String accion, LocalDateTime fecha) {
        this.user_id = user_id;
        this.accion = accion;
        this.fecha = fecha;
    }

    public user getUser_id() {
        return user_id;
    }

    public void setUser_id(user user_id) {
        this.user_id = user_id;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}