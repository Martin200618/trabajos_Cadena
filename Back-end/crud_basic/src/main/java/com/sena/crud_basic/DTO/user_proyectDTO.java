package com.sena.crud_basic.DTO;

import com.sena.crud_basic.model.proyects;
import com.sena.crud_basic.model.user;

public class user_proyectDTO {
    private user user_id;
    private proyects proyect_id;
    
    public user_proyectDTO() {
    }

    public user_proyectDTO(user user_id, proyects proyect_id) {
        this.user_id = user_id;
        this.proyect_id = proyect_id;
    }

    public user getUser_id() {
        return user_id;
    }

    public void setUser_id(user user_id) {
        this.user_id = user_id;
    }

    public proyects getProyect_id() {
        return proyect_id;
    }

    public void setProyect_id(proyects proyect_id) {
        this.proyect_id = proyect_id;
    }
}