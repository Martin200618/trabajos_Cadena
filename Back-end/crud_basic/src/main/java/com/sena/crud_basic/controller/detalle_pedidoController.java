package com.sena.crud_basic.controller;

import org.springframework.web.bind.annotation.RestController;
import com.sena.crud_basic.DTO.detalle_pedidoDTO;
import com.sena.crud_basic.service.detalle_pedidoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/v4/detalle_pedido")
public class detalle_pedidoController {
    @Autowired
    private detalle_pedidoService detalle_pedidoService;
    @PostMapping("/")
    public ResponseEntity<Object> registerDetalle_Pedido(@RequestBody detalle_pedidoDTO detalle_pedidoDTO){
        detalle_pedidoService.save(detalle_pedidoDTO);
        return new ResponseEntity<>("Register ok", HttpStatus.OK);
    }
}