package com.sena.crud_basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.DTO.OrdersDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.service.OrdersService;
@RestController
@RequestMapping("/api/v1/Orders")
public class OrdersController {

    @Autowired
    private OrdersService OrdersService;

    @PostMapping("/")
    public ResponseEntity<Object> registrarOrder(@RequestBody OrdersDTO Orders){
        responseDTO respuesta = OrdersService.save(Orders);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllOrders(){
        var listaOrders = OrdersService.finAll();
        return new ResponseEntity<>(listaOrders, HttpStatus.OK);
    }

    @GetMapping("/get/{orderId}")
    public ResponseEntity<Object> getOneOrders(@PathVariable int orderId){
        var Orders = OrdersService.findById(orderId);
        return new ResponseEntity<>(Orders, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity<Object> deleteOrders(@PathVariable int orderId){
        var Orders = OrdersService.findById(orderId);
        return new ResponseEntity<>(Orders, HttpStatus.OK);
    }

    // Nuevo endpoint para actualizar una orden
    @PutMapping("/update/{orderId}")
    public ResponseEntity<Object> updateOrder(@PathVariable int orderId, @RequestBody OrdersDTO Orders){
        // Llama al servicio para actualizar la orden
        responseDTO updatedOrder = OrdersService.update(orderId, Orders);
        
        // Verifica si la orden fue actualizada correctamente
        if (updatedOrder != null) {
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);  // OK si se actualizó
        } else {
            return new ResponseEntity<>("Order not found", HttpStatus.NOT_FOUND);  // Si no se encuentra la orden
        }
    }
}
