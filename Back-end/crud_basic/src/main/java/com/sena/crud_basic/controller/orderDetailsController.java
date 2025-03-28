package com.sena.crud_basic.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.DTO.orderDetailsDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.service.orderDetailsService;
@RestController
@RequestMapping("/api/v1/orderDetails")
public class orderDetailsController {
    @Autowired
    private orderDetailsService orderDetailsService;

    @PostMapping("/")
    public ResponseEntity<Object> registerOrderDetails(@RequestBody orderDetailsDTO orderDetails){
        responseDTO respuesta = orderDetailsService.save(orderDetails);
        return new ResponseEntity<>(respuesta,HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllOrderDetails(){
        var listaOrderDetails = orderDetailsService.findAll();
        return new ResponseEntity<>(listaOrderDetails,HttpStatus.OK);
    }
}
