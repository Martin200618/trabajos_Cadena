package com.sena.crud_basic.DTO;

public class AlbumesDTO {
    private String titulo;
    private String artista;
    private String fechaLanzamiento;

    public AlbumesDTO() {}

    public AlbumesDTO(String titulo, String artista, String fechaLanzamiento) {
        this.titulo = titulo;
        this.artista = artista;
        this.fechaLanzamiento = fechaLanzamiento;
    }

    // Getters y Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(String fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }
}