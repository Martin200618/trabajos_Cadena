package com.sena.crud_basic.DTO;

import com.sena.crud_basic.model.Activity;
import com.sena.crud_basic.model.proyects;

public class Activity_proyectDTO {
    private Activity activity_id;
    private proyects proyect_id;

    public Activity_proyectDTO() {
    }

    public Activity_proyectDTO(Activity activity_id, proyects proyect_id) {
        this.activity_id = activity_id;
        this.proyect_id = proyect_id;
    }

    public Activity getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(Activity activity_id) {
        this.activity_id = activity_id;
    }

    public proyects getProyect_id() {
        return proyect_id;
    }

    public void setProyect_id(proyects proyect_id) {
        this.proyect_id = proyect_id;
    }
}
