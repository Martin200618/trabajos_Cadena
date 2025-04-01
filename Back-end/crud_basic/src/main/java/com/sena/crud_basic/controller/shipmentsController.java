package com.sena.crud_basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.DTO.shipmentsDTO;
import com.sena.crud_basic.service.shipmentsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/v1/shipments")
public class shipmentsController {
    @Autowired
    private shipmentsService shipmentsService;

    @PostMapping("/")
    public ResponseEntity<Object> registrarshipments(@RequestBody shipmentsDTO shipments){
        responseDTO respuesta = shipmentsService.save(shipments);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllShipments(){
        var listShipments = shipmentsService.findAll();
        return new ResponseEntity<>(listShipments, HttpStatus.OK);
    }

    @GetMapping("/get/{shipmentId}")
    public ResponseEntity<Object> getOneShipments(@PathVariable int shipmentId){
        var shipments = shipmentsService.findById(shipmentId);
        return new ResponseEntity<>(shipments,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{shipmentId}")
    public ResponseEntity<Object> deleteShipments(@PathVariable int shipmentId){
        responseDTO respuesta = shipmentsService.deleteShipments(shipmentId);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @PutMapping("/update/{shipmentId}")
    public ResponseEntity<Object> updateShipments(@PathVariable int shipmentId, @RequestBody shipmentsDTO shipments){
        responseDTO updateShipments = shipmentsService.update(shipmentId, shipments);
        if (updateShipments!= null) {
            return new ResponseEntity<>(updateShipments, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se encontró el envío con el ID: " + shipmentId, HttpStatus.NOT_FOUND);
        }
    }
}