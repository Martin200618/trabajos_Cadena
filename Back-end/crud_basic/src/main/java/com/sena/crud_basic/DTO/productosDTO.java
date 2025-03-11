package com.sena.crud_basic.DTO;

public class productosDTO {
    private String nombre;
    private String descripcion;
    private double precio;
    private String cantidad;

    public productosDTO(String nombre, String descripcion, double precio, String cantidad){
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.precio=precio;
        this.cantidad=cantidad;
    }

    public String getnombre(){
        return nombre;
    }

    public void setnombre(String nombre){
        this.nombre=nombre;
    }

    public String getdescripcion(){
        return descripcion;
    }

    public void setdescripcion(String descripcion){
        this.descripcion=descripcion;
    }

    public double getprecio(){
        return precio;
    }

    public void setprecio(double precio){
        this.precio=precio;
    }
    
    public String getcantidad(){
        return cantidad;
    }

    public void setcantidad(String cantidad){
        this.cantidad=cantidad;
    }
}
