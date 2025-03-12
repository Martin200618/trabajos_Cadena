package com.sena.crud_basic.model;

import java.sql.Date;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name="pedidos")
public class pedidos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_pedido")
        private int id_pedido;
    @Column(name="fecha_pedido")
        private Date fecha_pedido;
    @Column(name="total")
        private double total;
    @Column(name="estado")
        private String estado;
    @ManyToOne
    @JoinColumn(name="id_usuario")
        private usuarios id_usuario;
    
    public pedidos(int id_pedido, Date fecha_pedido, double total, String estado, com.sena.crud_basic.model.usuarios id_usuario){
        this.id_pedido=id_pedido;
        this.fecha_pedido=fecha_pedido;
        this.total=total;
        this.estado=estado;
        this.id_usuario=id_usuario;
    }

    public pedidos(int i, LocalDateTime getfecha_pedido, double gettotal, String geestado) {
        //TODO Auto-generated constructor stub
    }

    public int getid_pedido(){
        return id_pedido;
    }
    public void setid_pedido(int id_pedido){
        this.id_pedido=id_pedido;
    }

    public Date getfecha_pedido(){
        return fecha_pedido;
    }
    public void setfecha_pedido(Date fecha_pedido){
        this.fecha_pedido=fecha_pedido;
    }

    public double gettotal(){
        return total;
    }
    public void setytotal(double total){
        this.total=total;
    }

    public String getestado(){
        return estado;
    }
    public void setestado(String estado){
        this.estado=estado;
    }

    public usuarios getid_usuario(){
        return id_usuario;
    }
    public void setid_usuario(com.sena.crud_basic.model.usuarios id_usuario){
        this.id_usuario=id_usuario;
    }
}
