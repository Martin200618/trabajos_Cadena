package com.sena.crud_basic.DTO;

import java.util.Date;

public class envioDTO {
    private String direccion;
    private String estado;
    private Date fecha_envio;

    public envioDTO(String direccion, String estado, Date fecha_envio){
        this.direccion=direccion;
        this.estado=estado;
        this.fecha_envio=fecha_envio;
    }

    public String getdireccion(){
        return direccion;
    }
    public void setdireccion(String direccion){
        this.direccion=direccion;
    }

    public String getestado(){
        return estado;
    }
    public void setestado(String estado){
        this.estado=estado;
    }

    public Date getfecha_envio(){
        return fecha_envio;
    }
    public void setfecha_envio(Date fecha_envio){
        this.fecha_envio=fecha_envio;
    }
}