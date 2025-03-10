package com.sena.crud_basic.controller;


import org.springframework.web.bind.annotation.RestController;
import com.sena.crud_basic.DTO.employeesDTO;
import com.sena.crud_basic.service.employeesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/v4/employees")
public class employeesController {
    @Autowired
    private employeesService employeesService;
    @PostMapping("/")
    public ResponseEntity<Object> registerEmployees(@RequestBody employeesDTO employees){
        employeesService.save(employees);
        return new ResponseEntity<>("Register ok", HttpStatus.OK);
    }
}
