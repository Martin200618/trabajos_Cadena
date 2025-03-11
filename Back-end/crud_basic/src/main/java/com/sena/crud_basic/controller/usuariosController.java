package com.sena.crud_basic.controller;

import org.springframework.web.bind.annotation.RestController;
import com.sena.crud_basic.DTO.usuariosDTO;
import com.sena.crud_basic.service.usuariosService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/v4/usuarios")
public class usuariosController {
    @Autowired
    private usuariosService usuariosService;
    @PostMapping("/")
    public ResponseEntity<Object> registerUsuarios(@RequestBody usuariosDTO usuariosDTO){
        usuariosService.save(usuariosDTO);
        return new ResponseEntity<>("Register ok", HttpStatus.OK);
    }
    
}
