package com.sena.crud_basic.DTO;

import java.sql.Time;

public class CancionesDTO {
    private String titulo;
    private Time duracion;
    private int albumId; // ID del álbum, en lugar del objeto completo
    private int generoId; // ID del género, en lugar de un String

    // Getters y Setters
    public int getAlbumId() {
        return albumId;
    }

    public CancionesDTO(String titulo, Time duracion, int albumId, int generoId) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.albumId = albumId;
        this.generoId = generoId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public int getGeneroId() {
        return generoId;
    }

    public void setGeneroId(int generoId) {
        this.generoId = generoId;
    }

    // Getters y Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Time getDuracion() {
        return duracion;
    }

    public void setDuracion(Time duracion) {
        this.duracion = duracion;
    }
}