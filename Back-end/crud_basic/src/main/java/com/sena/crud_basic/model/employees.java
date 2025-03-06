package com.sena.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="employees")
public class employees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="employee_id")
    private int employee_id;
    
    @Column(name="name", length=100, nullable=false)
    private String name;
    
    @Column(name="position", length=50, nullable=false)
    private String position;
    
    @Column(name="salary", length=20, nullable=false)
    private String salary;

    public employees(int employee_id, String name, String position, String salary){
        this.employee_id = employee_id;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    public int getEmployee_id(){
        return employee_id;
    }

    public void setEmployee_id(int employee_id){
        this.employee_id = employee_id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getPosition(){
        return position;
    }

    public void setPosition(String position){
        this.position = position;
    }

    public String getSalary(){
        return salary;
    }

    public void setSalary(String salary){
        this.salary = salary;
    }
}