package com.sena.crud_basic.controller;

import org.springframework.web.bind.annotation.RestController;
import com.sena.crud_basic.DTO.productosDTO;
import com.sena.crud_basic.service.productosService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/v4/productos")
public class productosController {
    @Autowired
    private productosService productosService;
    @PostMapping("/")
    public ResponseEntity<Object> registerProductos(@RequestBody productosDTO productosDTO){
        productosService.save(productosDTO);
        return new ResponseEntity<>("Register ok", HttpStatus.OK);
    }
        
}