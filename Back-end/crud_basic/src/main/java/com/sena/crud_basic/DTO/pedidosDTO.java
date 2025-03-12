package com.sena.crud_basic.DTO;

import java.sql.Date;
import java.time.LocalDateTime;

public class pedidosDTO {
    private LocalDateTime fecha_pedido;
    private double total;
    private String estado;

    public pedidosDTO(LocalDateTime fecha_pedido, double total, String estado){
        this.fecha_pedido=fecha_pedido;
        this.total=total;
        this.estado=estado;
    }

    public pedidosDTO(Date getfecha_pedido, double gettotal, String getestado) {
        //TODO Auto-generated constructor stub
    }

    public LocalDateTime getfecha_pedido(){
        return fecha_pedido;
    }
    public void setfecha_pedido(LocalDateTime fecha_pedido){
        this.fecha_pedido=fecha_pedido;
    }

    public double gettotal(){
        return total;
    }
    public void settotal(double total){
        this.total=total;
    }

    public String geestado(){
        return estado;
    }
    public void setestado(String estado){
        this.estado=estado;
    }
}
