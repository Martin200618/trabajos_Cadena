package com.sena.crud_basic.controller;

import org.springframework.web.bind.annotation.RestController;
import com.sena.crud_basic.DTO.pedidosDTO;
import com.sena.crud_basic.service.;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v4/pedidos")
public class pedidosController {
    @Autowired
    private pedidosService pedidosService;
    @PostMapping("/")
    public ResponseEntity<Object> registerPedido(@RequestBody pedidosDTO pedidosDTO){
        pedidosService.save(pedidosDTO);
        return new ResponseEntity<>("register ok", HttpStatus.OK);
    }   
}