package com.sena.crud_basic.controller;

import org.springframework.web.bind.annotation.RestController;
import com.sena.crud_basic.DTO.customersDTO;
import com.sena.crud_basic.service.customersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/v4/customers")
public class customersController{
    @Autowired
    private customersService customersService;
    @PostMapping("/")
    public ResponseEntity<Object> registerCustomers(@RequestBody customersDTO customersDTO){
        customersService.save(customersDTO);
        return new ResponseEntity<>("Register ok", HttpStatus.OK);
    }
}