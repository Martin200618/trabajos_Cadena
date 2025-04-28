package com.sena.crud_basic.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sena.crud_basic.DTO.GenerosDTO;
import com.sena.crud_basic.service.GenerosService;


@CrossOrigin(origins = "http://localhost:5500")
@RestController
@RequestMapping("api/v1/generos")
public class GenerosController {

    @Autowired
    private GenerosService generosService;

    @PostMapping("/")
    public ResponseEntity<Object> registerGenero(@RequestBody GenerosDTO genero) {
        String response = generosService.save(genero);
        return new ResponseEntity<>(
            response,
            response.startsWith("200") ? HttpStatus.OK : HttpStatus.BAD_REQUEST
        );
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllGeneros() {
        return new ResponseEntity<>(
            generosService.findAll(),
            HttpStatus.OK
        );
    }

    @GetMapping("/get/{generoId}")
    public ResponseEntity<Object> getOneGenero(@PathVariable int generoId) {
        Optional<Object> genero = Optional.ofNullable(generosService.findById(generoId));
        return genero.map(value -> new ResponseEntity<>(
            value,
            HttpStatus.OK
        )).orElseGet(() -> new ResponseEntity<>(
            "Genero not found",
            HttpStatus.NOT_FOUND
        ));
    }

    @PostMapping("/update/{generoId}")
    public ResponseEntity<Object> updateGenero(@PathVariable int generoId, @RequestBody GenerosDTO genero) {
        String response = generosService.update(generoId, genero);
        return new ResponseEntity<>(
            response,
            response.startsWith("200") ? HttpStatus.OK : HttpStatus.BAD_REQUEST
        );
    }

    @DeleteMapping("/delete/{generoId}")
    public ResponseEntity<Object> deleteGenero(@PathVariable int generoId) {
        String response = generosService.delete(generoId);
        return new ResponseEntity<>(
            response,
            response.startsWith("200") ? HttpStatus.OK : HttpStatus.BAD_REQUEST
        );
    }
}
