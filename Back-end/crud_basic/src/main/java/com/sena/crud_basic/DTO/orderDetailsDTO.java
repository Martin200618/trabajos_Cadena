package com.sena.crud_basic.DTO;

import com.sena.crud_basic.model.Orders;
import com.sena.crud_basic.model.products;

public class orderDetailsDTO {
    private Orders order_id;
    private products product_id;
    private double quantity;
    private double subtotal;
    private String imagen;

    public orderDetailsDTO() {
    }

    public orderDetailsDTO(Orders order_id, products product_id, double quantity, double subtotal, String imagen) {
        this.order_id = order_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.subtotal = subtotal;
        this.imagen = imagen;
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

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}