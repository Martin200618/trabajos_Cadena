package com.sena.crud_basic.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sena.crud_basic.DTO.ProveedoresDTO;
import com.sena.crud_basic.service.ProveedoresService;


@CrossOrigin(origins = "http://localhost:5500")
@RestController
@RequestMapping("api/v1/proveedores")
public class ProveedoresController {

    @Autowired
    private ProveedoresService proveedoresService;

    @PostMapping("/")
    public ResponseEntity<Object> registerProveedor(@RequestBody ProveedoresDTO proveedor) {
        String response = proveedoresService.save(proveedor);
        return new ResponseEntity<>(
            response,
            response.startsWith("200") ? HttpStatus.OK : HttpStatus.BAD_REQUEST
        );
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllProveedores() {
        return new ResponseEntity<>(
            proveedoresService.findAll(),
            HttpStatus.OK
        );
    }

    @GetMapping("/get/{proveedorId}")
    public ResponseEntity<Object> getOneProveedor(@PathVariable int proveedorId) {
        Optional<Object> proveedor = Optional.ofNullable(proveedoresService.findById(proveedorId));
        return proveedor.map(value -> new ResponseEntity<>(
            value,
            HttpStatus.OK
        )).orElseGet(() -> new ResponseEntity<>(
            "Proveedor not found",
            HttpStatus.NOT_FOUND
        ));
    }

    @PostMapping("/update/{proveedorId}")
    public ResponseEntity<Object> updateProveedor(@PathVariable int proveedorId, @RequestBody ProveedoresDTO proveedor) {
        String response = proveedoresService.update(proveedorId, proveedor);
        return new ResponseEntity<>(
            response,
            response.startsWith("200") ? HttpStatus.OK : HttpStatus.BAD_REQUEST
        );
    }

    @DeleteMapping("/delete/{proveedorId}")
    public ResponseEntity<Object> deleteProveedor(@PathVariable int proveedorId) {
        String response = proveedoresService.delete(proveedorId);
        return new ResponseEntity<>(
            response,
            response.startsWith("200") ? HttpStatus.OK : HttpStatus.BAD_REQUEST
        );
    }
}
