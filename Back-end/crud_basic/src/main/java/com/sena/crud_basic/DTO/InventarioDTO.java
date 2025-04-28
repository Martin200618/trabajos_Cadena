package com.sena.crud_basic.DTO;

public class InventarioDTO {
    private int album;
    private int proveedor;
    private int stock;
    private String fechaEntrada;

    public InventarioDTO() {}

    public InventarioDTO(int album, int proveedor, int stock, String fechaEntrada) {
        this.album = album;
        this.proveedor = proveedor;
        this.stock = stock;
        this.fechaEntrada = fechaEntrada;
    }

    // Getters y Setters
    public int getAlbum() {
        return album;
    }

    public void setAlbum(int album) {
        this.album = album;
    }

    public int getProveedor() {
        return proveedor;
    }

    public void setProveedor(int proveedor) {
        this.proveedor = proveedor;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }
}