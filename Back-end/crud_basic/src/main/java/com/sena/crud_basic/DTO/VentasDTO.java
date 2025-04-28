package com.sena.crud_basic.DTO;

public class VentasDTO {
    private String empleado;
    private String fecha;
    private double total;

    public VentasDTO() {}

    public VentasDTO(String empleado, String fecha, double total) {
        this.empleado = empleado;
        this.fecha = fecha;
        this.total = total;
    }

    // Getters y Setters
    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}