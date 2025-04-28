package com.sena.crud_basic.model;

import jakarta.persistence.*;
import java.sql.Date;

@Entity(name = "Ventas")
public class Ventas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Ventas_id")
    private int Ventas_id;

    @ManyToOne
    @JoinColumn(name = "id_empleado", nullable = false)
    private Empleados empleado;

    @Column(name = "fecha", nullable = false)
    private Date fecha;

    @Column(name = "total", nullable = false)
    private double total;

    public Ventas() {}

    public Ventas(int Ventas_id, Empleados empleado, Date fecha, double total) {
        this.Ventas_id = Ventas_id;
        this.empleado = empleado;
        this.fecha = fecha;
        this.total = total;
    }

    // Getters y Setters
    public int getVentas_id() {
        return Ventas_id;
    }

    public void setVentas_id(int Ventas_id) {
        this.Ventas_id = Ventas_id;
    }

    public Empleados getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleados empleado) {
        this.empleado = empleado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}