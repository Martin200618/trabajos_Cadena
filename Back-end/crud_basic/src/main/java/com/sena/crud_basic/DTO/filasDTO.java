package com.sena.crud_basic.DTO;

public class filasDTO {
    private double numero;
    private String descripcion;
    
    public filasDTO() {
    }
    
    public filasDTO(double numero, String descripcion) {
        this.numero = numero;
        this.descripcion = descripcion;
    }
    
    public double getNumero() {
        return numero;
    }
    
    public void setNumero(double numero) {
        this.numero = numero;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}