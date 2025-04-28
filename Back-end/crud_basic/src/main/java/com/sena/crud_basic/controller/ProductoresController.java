package com.sena.crud_basic.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sena.crud_basic.DTO.ProductoresDTO;
import com.sena.crud_basic.service.ProductoresService;


@CrossOrigin(origins = "http://localhost:5500")
@RestController
@RequestMapping("api/v1/productores")
public class ProductoresController {

    @Autowired
    private ProductoresService productoresService;

    @PostMapping("/")
    public ResponseEntity<Object> registerProductor(@RequestBody ProductoresDTO productor) {
        String response = productoresService.save(productor);
        return new ResponseEntity<>(
            response,
            response.startsWith("200") ? HttpStatus.OK : HttpStatus.BAD_REQUEST
        );
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllProductores() {
        return new ResponseEntity<>(
            productoresService.findAll(),
            HttpStatus.OK
        );
    }

    @GetMapping("/get/{productorId}")
    public ResponseEntity<Object> getOneProductor(@PathVariable int productorId) {
        Optional<Object> productor = Optional.ofNullable(productoresService.findById(productorId));
        return productor.map(value -> new ResponseEntity<>(
            value,
            HttpStatus.OK
        )).orElseGet(() -> new ResponseEntity<>(
            "Productor not found",
            HttpStatus.NOT_FOUND
        ));
    }

    @PostMapping("/update/{productorId}")
    public ResponseEntity<Object> updateProductor(@PathVariable int productorId, @RequestBody ProductoresDTO productor) {
        String response = productoresService.update(productorId, productor);
        return new ResponseEntity<>(
            response,
            response.startsWith("200") ? HttpStatus.OK : HttpStatus.BAD_REQUEST
        );
    }

    @DeleteMapping("/delete/{productorId}")
    public ResponseEntity<Object> deleteProductor(@PathVariable int productorId) {
        String response = productoresService.delete(productorId);
        return new ResponseEntity<>(
            response,
            response.startsWith("200") ? HttpStatus.OK : HttpStatus.BAD_REQUEST
        );
    }
}