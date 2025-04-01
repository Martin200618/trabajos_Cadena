package com.sena.crud_basic.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name="inventory")
public class inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="inventoryId")
        private int inventoryId;
    @ManyToOne
    @JoinColumn(name="productId")
        private products productId;
    @ManyToOne
    @JoinColumn(name="suppliersId")
        private suppliers suppliersId;
    @Column(name="quantity")
        private double quantity;
    @Column(name="lastUpdated")
        private LocalDateTime lastUpdated;
    
    public inventory() {
    }

    public inventory(int inventoryId, products productId, suppliers suppliersId, double quantity, LocalDateTime lastUpdated) {
        this.inventoryId = inventoryId;
        this.productId = productId;
        this.suppliersId = suppliersId;
        this.quantity = quantity;
        this.lastUpdated = lastUpdated;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public products getProductId() {
        return productId;
    }

    public void setProductId(products productId) {
        this.productId = productId;
    }

    public suppliers getSuppliersId() {
        return suppliersId;
    }

    public void setSuppliersId(suppliers suppliersId) {
        this.suppliersId = suppliersId;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}