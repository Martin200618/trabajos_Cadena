package com.sena.crud_basic.model;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name="activity")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="activity_id")
    private int activity_id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private user user_id;

    @Column(name="accion", nullable = false)
    private String accion;

    @Column(name="fecha", nullable = false)
    private LocalDateTime fecha;

    public Activity() {
    }

    public Activity(int activity_id, user user_id, String accion, LocalDateTime fecha) {
        this.activity_id = activity_id;
        this.user_id = user_id;
        this.accion = accion;
        this.fecha = fecha;
    }

    public int getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(int activity_id) {
        this.activity_id = activity_id;
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