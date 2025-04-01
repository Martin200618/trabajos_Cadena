package com.sena.crud_basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.DTO.paymentMethodsDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.service.paymentMethodsService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/v1/methods")
public class paymentMethodsController {
    @Autowired
    private paymentMethodsService paymentMethodsService;

    @PostMapping("/")
    public ResponseEntity<Object> registrarPaymentMethods(@RequestBody paymentMethodsDTO paymentMethods){
        responseDTO respuesta = paymentMethodsService.save(paymentMethods);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllMethods(){
        var listMethods = paymentMethodsService.finAll();
        return new ResponseEntity<>(listMethods, HttpStatus.OK);
    }

    @GetMapping("/get/{paymentId}")
    public ResponseEntity<Object> getOneMethod(@PathVariable int paymentId){
        var paymentMethods = paymentMethodsService.findById(paymentId);
        return new ResponseEntity<>(paymentMethods, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{paymentId}")
    public ResponseEntity<Object> deleteMethods(@PathVariable int paymentId){
        var respuesta = paymentMethodsService.deleteMethods(paymentId);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @PutMapping("update/{paymentId}")
    public ResponseEntity<Object> updatePaymentMethods(@PathVariable int paymentId, @RequestBody paymentMethodsDTO paymentMethods){
        responseDTO updatedPaymentMethods = paymentMethodsService.update(paymentId, paymentMethods.getMethod());
        if (updatedPaymentMethods!= null) {
            return new ResponseEntity<>(updatedPaymentMethods, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Payment method not found", HttpStatus.NOT_FOUND);
        }
    }
}