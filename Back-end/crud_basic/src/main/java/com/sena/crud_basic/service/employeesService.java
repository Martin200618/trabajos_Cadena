package com.sena.crud_basic.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.sena.crud_basic.model.employees;
import com.sena.crud_basic.DTO.employeesDTO;
import com.sena.crud_basic.repository.Iemployees;

@Service
public class employeesService {
    @Autowired
    private Iemployees data;

    public void save(employeesDTO employeesDTO){
        employees employeesRegister = converToModel(employeesDTO);
        data.save(employeesRegister);
    }

    public employeesDTO converToDTO(employees employees){
        employeesDTO employeesDTO = new employeesDTO(
            employees.getName(),
            employees.getPosition(),
            employees.getSalary());
        return employeesDTO;
    }

    public employees converToModel(employeesDTO employeesDTO){
        employees employees = new employees(
            0,
            employeesDTO.getname(),
            employeesDTO.getposition(),
            employeesDTO.getsalary());
        return employees;
    }
}
