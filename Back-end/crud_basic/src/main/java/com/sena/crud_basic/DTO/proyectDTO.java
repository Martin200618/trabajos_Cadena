package com.sena.crud_basic.DTO;

public class proyectDTO {
    private String name;
    private String imagenBase64;
    private String descripcion;
    
    public proyectDTO() {
    }
    
    public proyectDTO(String name, String imagenBase64, String descripcion) {
        this.name = name;
        this.imagenBase64 = imagenBase64;
        this.descripcion = descripcion;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getimagenBase64() {
        return imagenBase64;
    }
    
    public void setimagenBase64(String imagenBase64) {
        this.imagenBase64 = imagenBase64;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}