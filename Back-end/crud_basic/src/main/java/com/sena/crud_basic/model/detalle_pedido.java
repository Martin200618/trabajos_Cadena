package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name="detalle_pedido")
public class detalle_pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_detalle")
        private int id_detalle;
    @Column(name="cantidad")
        private String cantidad;
    @Column(name="subtotal")
        private double subtotal;
    @ManyToOne
    @JoinColumn(name="id_pedido")
        private pedidos id_pedido;
    @ManyToOne
    @JoinColumn(name="id-producto")
        private productos id_productos;

    public detalle_pedido(int id_detalle, String cantidad, double subtotal, com.sena.crud_basic.model.pedidos id_pedido, com.sena.crud_basic.model.productos id_producto){
        this.id_detalle=id_detalle;
        this.cantidad=cantidad;
        this.subtotal=subtotal;
        this.id_pedido=id_pedido;
        this.id_productos=id_producto;
    }
    public detalle_pedido(int i, String getcantidad, double getsubtotal) {
        //TODO Auto-generated constructor stub
    }
    public int getid_detalle(){
        return id_detalle;
    }
    public void setid_detalle(int id_detalle){
        this.id_detalle=id_detalle;
    }

    public String getcantidad(){
        return cantidad;
    }
    public void setcantidad(String cantidad){
        this.cantidad=cantidad;
    }

    public double getsubtotal(){
        return subtotal;
    }
    public void setsubtotal(double subtotal){
        this.subtotal=subtotal;
    }

    public pedidos getid_pedido(){
        return id_pedido;
    }
    public void setid_pedido(pedidos id_pedido){
        this.id_pedido=id_pedido;
    }

    public productos getid_producto(){
        return id_productos;
    }
    public void setid_producto(productos id_Producto){
        this.id_productos=id_Producto;
    }
}
