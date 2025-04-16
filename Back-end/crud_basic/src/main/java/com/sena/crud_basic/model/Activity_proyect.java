package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "activity_proyect")
public class Activity_proyect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activity_proyect_id")
    private int activity_proyect_id;

    @ManyToOne
    @JoinColumn(name = "activity_id", nullable = false)
    private Activity activity_id;

    @ManyToOne
    @JoinColumn(name = "proyect_id", nullable = false)
    private proyects proyect_id;

    public Activity_proyect() {
    }

    public Activity_proyect(int activity_proyect_id, Activity activity_id, proyects proyect_id) {
        this.activity_proyect_id = activity_proyect_id;
        this.activity_id = activity_id;
        this.proyect_id = proyect_id;
    }

    public int getActivity_proyect_id() {
        return activity_proyect_id;
    }

    public void setActivity_proyect_id(int activity_proyect_id) {
        this.activity_proyect_id = activity_proyect_id;
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