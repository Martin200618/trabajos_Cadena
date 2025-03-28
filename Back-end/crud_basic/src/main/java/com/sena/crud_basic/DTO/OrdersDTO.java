package com.sena.crud_basic.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.sena.crud_basic.model.status;
import com.sena.crud_basic.model.user;

public class OrdersDTO {
    private int orderId;
    private LocalDateTime orderDate;
    private BigDecimal total;
    private status status;
    private user user;
    public OrdersDTO() {}
    public OrdersDTO(int orderId, user user, LocalDateTime orderDate, BigDecimal total, com.sena.crud_basic.model.status status) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.total = total;
        this.status = status;
    }
    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public user getUser() {
        return user;
    }   
    public void setUser(user user){
        this.user = user;
    }
    public LocalDateTime getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
    public BigDecimal getTotal() {
        return total;
    }
    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    public status getStatus() {
        return status;
    }
    public void setStatus(status status) {
        this.status = status;
    }
}