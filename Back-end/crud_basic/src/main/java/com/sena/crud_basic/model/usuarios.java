package com.sena.crud_basic.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="usuarios")
public class usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_usuario")
        private int id_usuario;
    @Column(name="nombre",length = 100,nullable = false)
        private String nombre;
    @Column(name="email",length = 100,nullable = false)
        private String email;
    @Column(name="contrasena",length = 100,nullable = false)
        private String contrasena;
    @Column(name="direccion",length = 225,nullable = false)
        private String direccion;
    @Column(name="telefono",length = 20)
        private String telefono;
    @Column(name="fecha_registro")
        private LocalDateTime fecha_registro;
    
    public usuarios (int id_usuario, String nombre, String email, String contrasena, String direccion, String telefono, LocalDateTime fecha_registro){
        this.id_usuario=id_usuario;
        this.nombre=nombre;
        this.email=email;
        this.contrasena=contrasena;
        this.direccion=direccion;
        this.telefono=telefono;
        this.fecha_registro=fecha_registro;
    }

    public int getid_usuario(){
        return id_usuario;
    }
    public void setid_usuario(int id_usuario){
        this.id_usuario=id_usuario;
    }

    public String getnombre(){
        return nombre;
    }
    public void setnombre(String nombre){
        this.nombre=nombre;
    }

    public String getemail(){
        return email;
    }
    public void setemail(String email){
        this.email=email;
    }

    public String getcontrasena(){
        return contrasena;
    }
    public void setcontrasena(String contrasena){
        this.contrasena=contrasena;
    }

    public String getdireccion(){
        return direccion;
    }
    public void setdireccion(String direccion){
        this.direccion=direccion;
    }

    public String gettelefono(){
        return telefono;
    }
    public void settelefono(String telefono){
        this.telefono=telefono;
    }

    public LocalDateTime getfecha_registro(){
        return fecha_registro;
    }
    public void setfecha_registro(LocalDateTime fecha_registro){
        this.fecha_registro=fecha_registro;
    }
}