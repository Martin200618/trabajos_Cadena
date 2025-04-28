package com.sena.crud_basic.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sena.crud_basic.DTO.InventarioDTO;
import com.sena.crud_basic.service.InventarioService;


@CrossOrigin(origins = "http://localhost:5500")
@RestController
@RequestMapping("api/v1/inventario")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @PostMapping("/")
    public ResponseEntity<Object> registerInventario(@RequestBody InventarioDTO inventario) {
        String response = inventarioService.save(inventario);
        return new ResponseEntity<>(
            response,
            response.startsWith("200") ? HttpStatus.OK : HttpStatus.BAD_REQUEST
        );
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllInventario() {
        return new ResponseEntity<>(
            inventarioService.findAll(),
            HttpStatus.OK
        );
    }

    @GetMapping("/get/{inventarioId}")
    public ResponseEntity<Object> getOneInventario(@PathVariable int inventarioId) {
        Optional<Object> inventario = Optional.ofNullable(inventarioService.findById(inventarioId));
        return inventario.map(value -> new ResponseEntity<>(
            value,
            HttpStatus.OK
        )).orElseGet(() -> new ResponseEntity<>(
            "Inventario not found",
            HttpStatus.NOT_FOUND
        ));
    }

    @PostMapping("/update/{inventarioId}")
    public ResponseEntity<Object> updateInventario(@PathVariable int inventarioId, @RequestBody InventarioDTO inventario) {
        String response = inventarioService.update(inventarioId, inventario);
        return new ResponseEntity<>(
            response,
            response.startsWith("200") ? HttpStatus.OK : HttpStatus.BAD_REQUEST
        );
    }

    @DeleteMapping("/delete/{inventarioId}")
    public ResponseEntity<Object> deleteInventario(@PathVariable int inventarioId) {
        String response = inventarioService.delete(inventarioId);
        return new ResponseEntity<>(
            response,
            response.startsWith("200") ? HttpStatus.OK : HttpStatus.BAD_REQUEST
        );
    }
}