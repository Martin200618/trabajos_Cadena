package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="categorias")
public class categorias {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_categorias")
        private int id_categorias;
    @Column(name="nombre",length = 100,nullable = false)
        private String nombre;
    
    public categorias(int id_categorias, String nombre){
        this.id_categorias=id_categorias;
        this.nombre=nombre;
    }
    public int getid_categorias(){
        return id_categorias;
    }
    public void setid_categorias(int id_categorias){
        this.id_categorias=id_categorias;
    }
}
