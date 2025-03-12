package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name="productos")
public class productos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_producto")
        private int id_producto;
    @Column(name="nombre",length = 100,nullable = false)
        private String nombre;
    @Column(name="descripcion",length = 100,nullable = false)
        private String descripcion;
    @Column(name="precio")
        private double precio;
    @Column(name="cantidad",length=100,nullable=false)
        private String cantidad;
    @ManyToOne
    @JoinColumn(name="id_categoria")
        private categorias id_categorias;

    public productos(int id_producto, String nombre, String descripcion, double precio, String cantidad, com.sena.crud_basic.model.categorias id_categorias){
            this.id_producto=id_producto;
            this.nombre=nombre;
            this.descripcion=descripcion;
            this.precio=precio;
            this.cantidad=cantidad;
            this.id_categorias=id_categorias;
    }
    public productos(int i, String getnombre, String getdescripcion, double getprecio, String getcantidad) {
    }
    public int getid_producto(){
        return id_producto;
    }
    public void setid_producto(int id_producto){
        this.id_producto=id_producto;
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

    public categorias getid_categorias(){
        return id_categorias;
    }
    public void setid_categorias(categorias id_categorias){
        this.id_categorias=id_categorias;
    }
}
