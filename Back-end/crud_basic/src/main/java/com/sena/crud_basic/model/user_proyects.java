package com.sena.crud_basic.model;

import jakarta.persistence.*;

@Entity(name = "user_proyects")
public class user_proyects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_proyect_id")
    private int user_proyect_id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private user user_id;

    @ManyToOne
    @JoinColumn(name = "proyect_id", nullable = false)
    private proyects proyect_id;

    public user_proyects() {
    }

    public user_proyects(int user_proyect_id, user user_id, proyects proyect_id) {
        this.user_proyect_id = user_proyect_id;
        this.user_id = user_id;
        this.proyect_id = proyect_id;
    }

    public int getUser_proyect_id() {
        return user_proyect_id;
    }

    public void setUser_proyect_id(int user_proyect_id) {
        this.user_proyect_id = user_proyect_id;
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