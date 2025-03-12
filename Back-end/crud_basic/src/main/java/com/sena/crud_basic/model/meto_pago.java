package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="meto_pago")
public class meto_pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_pago")
        private int id_pago;
    @Column(name="meto",length = 50,nullable = false)
        private String metodo;
    
    public meto_pago(int id_pago, String metodo){
        this.id_pago=id_pago;
        this.metodo=metodo;
    }

    public int getid_pago(){
        return id_pago;
    }
    public void setid_pago(int id_pago){
        this.id_pago=id_pago;
    }

    public String getmetodo(){
        return metodo;
    }
    public void setmetodo(String metodo){
        this.metodo=metodo;
    }
}
