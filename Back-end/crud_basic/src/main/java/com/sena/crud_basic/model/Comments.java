package com.sena.crud_basic.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name="comments")
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="comment_id")
    private int comment_id;

    @ManyToOne
    @JoinColumn(name="proyecto_id", nullable = false)
    private filas proyectId;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private user user_id;

    @Column(name="texto", nullable = false)
    private String texto;

    @Column(name="fecha_creacion", nullable = false)
    private LocalDateTime fecha_creacion;

    public Comments() {}

    public Comments(int comment_id, filas proyectId, user user_id, String texto, LocalDateTime fecha_creacion) {
        this.comment_id = comment_id;
        this.proyectId = proyectId;
        this.user_id = user_id;
        this.texto = texto;
        this.fecha_creacion = fecha_creacion;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public filas getProyectId() {
        return proyectId;
    }

    public void setProyectId(filas proyectId) {
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