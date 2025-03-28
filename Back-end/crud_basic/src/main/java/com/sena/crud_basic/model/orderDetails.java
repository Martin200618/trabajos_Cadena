package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="orderDetails")
public class orderDetails {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="orderDetails_id")
        private int orderDetails_id;
    
    @Column(name="order_id")
        private Orders order_id;

    @Column(name="product_id")
        private products product_id;

    @Column(name="quantity")
        private int quantity;

    @Column(name="subtotal")
        private double subtotal;

    @Column(name="image")
        private String image;

    public orderDetails() {
    }

    public orderDetails(int orderDetails_id, Orders order_id, products product_id, int quantity, double subtotal, String image) {
        this.orderDetails_id = orderDetails_id;
        this.order_id = order_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.subtotal = subtotal;
        this.image = image;
    }

    public int getOrderDetails_id() {
        return orderDetails_id;
    }

    public void setOrderDetails_id(int orderDetails_id) {
        this.orderDetails_id = orderDetails_id;
    }

    public Orders getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Orders order_id) {
        this.order_id = order_id;
    }

    public products getProduct_id() {
        return product_id;
    }

    public void setProduct_id(products product_id) {
        this.product_id = product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}