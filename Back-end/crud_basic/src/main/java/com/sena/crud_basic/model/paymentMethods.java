package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="payment")
public class paymentMethods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="paymentId")
        private int paymentId;
    @Column(name="method",length = 50)
        private String method;

    public paymentMethods() {
    }

    public paymentMethods(int paymentId, String method) {
        this.paymentId = paymentId;
        this.method = method;
    }

    public int getPaymentId() {
        return paymentId;
    }
    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }
    public String getMethod() {
        return method;
    }
    public void setMethod(String method) {
        this.method = method;
    }
}
