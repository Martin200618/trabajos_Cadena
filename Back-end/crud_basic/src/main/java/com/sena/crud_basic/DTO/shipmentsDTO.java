package com.sena.crud_basic.DTO;

import java.time.LocalDateTime;

import com.sena.crud_basic.model.Orders;

public class shipmentsDTO {
    private Orders orderId;
    private String address;
    private String estatus;
    private LocalDateTime date;

    public shipmentsDTO() {
    }

    public shipmentsDTO(Orders orderId, String address, String estatus, LocalDateTime date) {
        this.orderId = orderId;
        this.address = address;
        this.estatus = estatus;
        this.date = date;
    }

    public Orders getOrderId() {
        return orderId;
    }

    public void setOrderId(Orders orderId) {
        this.orderId = orderId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
