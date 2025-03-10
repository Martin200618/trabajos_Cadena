package com.sena.crud_basic.DTO;

public class employeesDTO {
    private String name;

    private String position;

    private String salary;

    public employeesDTO(String name, String position, String salary){
        this.name=name;
        this.position=position;
        this.salary=salary;
    }

    public String getname(){
        return name;
    }

    public void setname(String name){
        this.name=name;
    }

    public String getposition(){
        return position;
    }

    public void setposition(String position){
        this.position=position;
    }

    public String getsalary(){
        return salary;
    }

    public void setsalary(String salary){
        this.salary=salary;
    }
}
