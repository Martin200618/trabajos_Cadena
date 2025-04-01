package com.sena.crud_basic.DTO;

public class paymentMethodsDTO {
    private String method;

    public paymentMethodsDTO() {
    }

    public paymentMethodsDTO(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
