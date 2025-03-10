package com.sena.crud_basic.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.sena.crud_basic.model.customers;
import com.sena.crud_basic.DTO.customersDTO;
import com.sena.crud_basic.repository.Icustomers;

@Service
public class customersService {
    @Autowired
    private Icustomers data;

    public void save(customersDTO customersDTO){
        customers customersRegister = converToModel(customersDTO);
        data.save(customersRegister);
    }

    public customersDTO converToDTO(com.sena.crud_basic.model.customers customers){
        customersDTO customersDTO = new customersDTO(
            customers.getname(),
            customers.getemail(),
            customers.getphone(),
            customers.getaddress());
        return customersDTO;
    }

    public customers converToModel(customersDTO customersDTO){
        customers customers = new customers(
            0,
            customersDTO.getname(),
            customersDTO.getemail(),
            customersDTO.getphone(),
            customersDTO.getaddress());
        return customers;
    }
}