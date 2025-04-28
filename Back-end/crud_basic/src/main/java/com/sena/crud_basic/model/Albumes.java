package com.sena.crud_basic.model;

import jakarta.persistence.*;
import java.sql.Date;

@Entity(name = "Albumes")
public class Albumes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Album_id")
    private int Album_id;

    @Column(name = "titulo", length = 100, nullable = false)
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "id_artista", nullable = false)
    private Artistas artista;

    @Column(name = "fecha_lanzamiento")
    private Date fechaLanzamiento;

    public Albumes() {}

    public Albumes(int Album_id, String titulo, Artistas artista, Date fechaLanzamiento) {
        this.Album_id = Album_id;
        this.titulo = titulo;
        this.artista = artista;
        this.fechaLanzamiento = fechaLanzamiento;
    }

    // Getters y Setters
    public int getAlbum_id() {
        return Album_id;
    }

    public void setAlbum_id(int Album_id) {
        this.Album_id = Album_id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Artistas getArtista() {
        return artista;
    }

    public void setArtista(Artistas artista) {
        this.artista = artista;
    }

    public Date getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(Date fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }
}