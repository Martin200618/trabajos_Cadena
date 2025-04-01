package com.sena.crud_basic.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name="shipments")
public class shipments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="shipmentId")
        private int shipmentId;
    @ManyToOne
    @JoinColumn(name="orderId")
        private Orders orderId;
    @Column(name="address")
        private String address;
    @Column(name="estatus")
        private String estatus;
    @Column(name="date")
        private LocalDateTime date;

    public shipments() {
    }

    public shipments(int shipmentId, Orders orderId, String address, String estatus, LocalDateTime date) {
        this.shipmentId = shipmentId;
        this.orderId = orderId;
        this.address = address;
        this.estatus = estatus;
        this.date = date;
    }

    public int getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(int shipmentId) {
        this.shipmentId = shipmentId;
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
