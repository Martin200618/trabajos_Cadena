package com.sena.crud_basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.DTO.inventoryDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.service.inventoryService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/v1/inventory")
public class inventoryController {
    @Autowired
    private inventoryService inventoryService;
    @PostMapping("/")
    public ResponseEntity<Object> registrarInventary(@RequestBody inventoryDTO inventory){
        responseDTO respuesta = inventoryService.save(inventory);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllInventory(){
        var ListaInventary = inventoryService.findAll();
        return new ResponseEntity<>(ListaInventary, HttpStatus.OK);
    }

    @GetMapping("/get/inventoryId")
    public ResponseEntity<Object> getOneInventory(@PathVariable int inventoryId){
        var inventory = inventoryService.findById(inventoryId);
        return new ResponseEntity<>(inventory, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{inventoryId}")
    public ResponseEntity<Object> deleteInventory(@PathVariable int inventoryId){
        var inventory = inventoryService.findById(inventoryId);
        return new ResponseEntity<>(inventory, HttpStatus.OK);
    }

    @PutMapping("/update/{inventoryId}")
    public ResponseEntity<Object> updateInventory(@PathVariable int inventoryId, @RequestBody inventoryDTO inventory){
        responseDTO updatedInventory = inventoryService.update(inventoryId, inventory);
        if (updatedInventory!= null) {
            return new ResponseEntity<>(updatedInventory, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se encontró el inventario con el id: " + inventoryId, HttpStatus.NOT_FOUND);
        }
    }
}