package com.sena.crud_basic.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sena.crud_basic.DTO.CancionesDTO;
import com.sena.crud_basic.service.CancionesService;


@CrossOrigin(origins = "http://localhost:5500")
@RestController
@RequestMapping("api/v1/canciones")
public class CancionesController {

    @Autowired
    private CancionesService cancionesService;

    @PostMapping("/")
    public ResponseEntity<Object> registerCancion(@RequestBody CancionesDTO cancion) {
        String response = cancionesService.save(cancion);
        return new ResponseEntity<>(
            response,
            response.startsWith("200") ? HttpStatus.OK : HttpStatus.BAD_REQUEST
        );
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllCanciones() {
        return new ResponseEntity<>(
            cancionesService.findAll(),
            HttpStatus.OK
        );
    }

    @GetMapping("/get/{cancionId}")
    public ResponseEntity<Object> getOneCancion(@PathVariable int cancionId) {
        Optional<Object> cancion = Optional.ofNullable(cancionesService.findById(cancionId));
        return cancion.map(value -> new ResponseEntity<>(
            value,
            HttpStatus.OK
        )).orElseGet(() -> new ResponseEntity<>(
            "Canción not found",
            HttpStatus.NOT_FOUND
        ));
    }

    @PostMapping("/update/{cancionId}")
    public ResponseEntity<Object> updateCancion(@PathVariable int cancionId, @RequestBody CancionesDTO cancion) {
        String response = cancionesService.update(cancionId, cancion);
        return new ResponseEntity<>(
            response,
            response.startsWith("200") ? HttpStatus.OK : HttpStatus.BAD_REQUEST
        );
    }

    @DeleteMapping("/delete/{cancionId}")
    public ResponseEntity<Object> deleteCancion(@PathVariable int cancionId) {
        String response = cancionesService.delete(cancionId);
        return new ResponseEntity<>(
            response,
            response.startsWith("200") ? HttpStatus.OK : HttpStatus.BAD_REQUEST
        );
    }
}