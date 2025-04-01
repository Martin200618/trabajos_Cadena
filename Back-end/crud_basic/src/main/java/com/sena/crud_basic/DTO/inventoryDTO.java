package com.sena.crud_basic.DTO;

import java.time.LocalDateTime;

import com.sena.crud_basic.model.products;
import com.sena.crud_basic.model.suppliers;

public class inventoryDTO {
    public products product_id;
    public suppliers suppliersId;
    public double quantity;
    public LocalDateTime lastUpdated;
    
    public inventoryDTO() {
    }

    public inventoryDTO(products product_id, suppliers suppliersId, double quantity, LocalDateTime lastUpdated) {
        this.product_id = product_id;
        this.suppliersId = suppliersId;
        this.quantity = quantity;
        this.lastUpdated = lastUpdated;
    }

    public products getProduct_id() {
        return product_id;
    }

    public void setProduct_id(products product_id) {
        this.product_id = product_id;
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