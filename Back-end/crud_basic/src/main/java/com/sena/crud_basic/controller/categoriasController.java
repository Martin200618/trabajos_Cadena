package com.sena.crud_basic.controller;

import org.springframework.web.bind.annotation.RestController;
import com.sena.crud_basic.DTO.categoriasDTO;
import com.sena.crud_basic.service.categoriasService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/v4/categorias")
public class categoriasController {
    @Autowired
    private categoriasService categoriasService;
    @PostMapping("/")
    public ResponseEntity<Object> registerUsuarios(@RequestBody categoriasDTO categoriasDTO){
        categoriasService.save(categoriasDTO);
        return new ResponseEntity<>("Register ok", HttpStatus.OK);
    }
}
