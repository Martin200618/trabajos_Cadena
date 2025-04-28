package com.sena.crud_basic.model;

import jakarta.persistence.*;

@Entity(name = "Canciones")
public class Canciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Canciones_id")
    private int Canciones_id;

    @Column(name = "titulo", length = 100, nullable = false)
    private String titulo;

    @Column(name = "duracion", nullable = false)
    private java.sql.Time duracion;

    @ManyToOne
    @JoinColumn(name = "id_album", nullable = false)
    private Albumes album;

    @ManyToOne
    @JoinColumn(name = "id_genero", nullable = false)
    private Generos genero;

    @ManyToOne
    @JoinColumn(name = "id_productor", nullable = false)
    private Productores productor;

    @ManyToOne
    @JoinColumn(name = "id_estudio", nullable = false)
    private EstudiosGrabacion estudio;

    public Canciones() {}

    public Canciones(int Canciones_id, String titulo, java.sql.Time duracion, Albumes album, Generos genero, Productores productor, EstudiosGrabacion estudio) {
        this.Canciones_id = Canciones_id;
        this.titulo = titulo;
        this.duracion = duracion;
        this.album = album;
        this.genero = genero;
        this.productor = productor;
        this.estudio = estudio;
    }

    // Getters y Setters
    public int getCanciones_id() {
        return Canciones_id;
    }

    public void setCanciones_id(int Canciones_id) {
        this.Canciones_id = Canciones_id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public java.sql.Time getDuracion() {
        return duracion;
    }

    public void setDuracion(java.sql.Time duracion) {
        this.duracion = duracion;
    }

    public Albumes getAlbum() {
        return album;
    }

    public void setAlbum(Albumes album) {
        this.album = album;
    }

    public Generos getGenero() {
        return genero;
    }

    public void setGenero(Generos genero) {
        this.genero = genero;
    }

    public Productores getProductor() {
        return productor;
    }

    public void setProductor(Productores productor) {
        this.productor = productor;
    }

    public EstudiosGrabacion getEstudio() {
        return estudio;
    }

    public void setEstudio(EstudiosGrabacion estudio) {
        this.estudio = estudio;
    }
}