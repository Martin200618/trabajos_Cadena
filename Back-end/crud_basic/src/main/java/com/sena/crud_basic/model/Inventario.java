package com.sena.crud_basic.model;

import jakarta.persistence.*;
import java.sql.Date;

@Entity(name = "Inventario")
public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Inventario_id")
    private int Inventario_id;

    @ManyToOne
    @JoinColumn(name = "id_album", nullable = false)
    private Albumes album;

    @ManyToOne
    @JoinColumn(name = "id_proveedor", nullable = false)
    private Proveedores proveedor;

    @Column(name = "stock", nullable = false)
    private int stock;

    @Column(name = "fecha_entrada")
    private Date fechaEntrada;

    public Inventario() {}

    public Inventario(int Inventario_id, Albumes album, Proveedores proveedor, int stock, Date fechaEntrada) {
        this.Inventario_id = Inventario_id;
        this.album = album;
        this.proveedor = proveedor;
        this.stock = stock;
        this.fechaEntrada = fechaEntrada;
    }

    // Getters y Setters
    public int getInventario_id() {
        return Inventario_id;
    }

    public void setInventario_id(int Inventario_id) {
        this.Inventario_id = Inventario_id;
    }

    public Albumes getAlbum() {
        return album;
    }

    public void setAlbum(Albumes album) {
        this.album = album;
    }

    public Proveedores getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedores proveedor) {
        this.proveedor = proveedor;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }
}