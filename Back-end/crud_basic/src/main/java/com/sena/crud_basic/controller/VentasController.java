package com.sena.crud_basic.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sena.crud_basic.DTO.VentasDTO;
import com.sena.crud_basic.service.VentasService;


@CrossOrigin(origins = "http://localhost:5500")
@RestController
@RequestMapping("api/v1/ventas")
public class VentasController {

    @Autowired
    private VentasService ventasService;

    @PostMapping("/")
    public ResponseEntity<Object> registerVenta(@RequestBody VentasDTO venta) {
        String response = ventasService.save(venta);
        return new ResponseEntity<>(
            response,
            response.startsWith("200") ? HttpStatus.OK : HttpStatus.BAD_REQUEST
        );
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllVentas() {
        return new ResponseEntity<>(
            ventasService.findAll(),
            HttpStatus.OK
        );
    }

    @GetMapping("/get/{ventaId}")
    public ResponseEntity<Object> getOneVenta(@PathVariable int ventaId) {
        Optional<Object> venta = Optional.ofNullable(ventasService.findById(ventaId));
        return venta.map(value -> new ResponseEntity<>(
            value,
            HttpStatus.OK
        )).orElseGet(() -> new ResponseEntity<>(
            "Venta not found",
            HttpStatus.NOT_FOUND
        ));
    }

    @PostMapping("/update/{ventaId}")
    public ResponseEntity<Object> updateVenta(@PathVariable int ventaId, @RequestBody VentasDTO venta) {
        String response = ventasService.update(ventaId, venta);
        return new ResponseEntity<>(
            response,
            response.startsWith("200") ? HttpStatus.OK : HttpStatus.BAD_REQUEST
        );
    }

    @DeleteMapping("/delete/{ventaId}")
    public ResponseEntity<Object> deleteVenta(@PathVariable int ventaId) {
        String response = ventasService.delete(ventaId);
        return new ResponseEntity<>(
            response,
            response.startsWith("200") ? HttpStatus.OK : HttpStatus.BAD_REQUEST
        );
    }
}