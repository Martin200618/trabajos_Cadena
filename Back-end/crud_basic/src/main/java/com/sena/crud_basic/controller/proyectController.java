package com.sena.crud_basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sena.crud_basic.DTO.proyectDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.service.proyectService;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5500")
@RestController
@RequestMapping("api/v1/proyects")
public class proyectController {

    @Autowired
    private proyectService proyectService;

    // Registrar proyecto
    @PostMapping
    public ResponseEntity<Object> registerProyect(@RequestBody proyectDTO proyects) {
        responseDTO response = proyectService.save(proyects);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    // Obtener todos los proyectos
    @GetMapping("/")
    public ResponseEntity<Object> getAllProyects() {
        return new ResponseEntity<>(proyectService.findAll(), HttpStatus.OK);
    }

    // Obtener un proyecto por ID
    @GetMapping("/get/{proyectId}")
    public ResponseEntity<Object> getOneProyect(@PathVariable int proyectId) {
        Optional<Object> proyecto = Optional.ofNullable(proyectService.findById(proyectId));
        return proyecto.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                       .orElseGet(() -> new ResponseEntity<>("Proyecto no encontrado", HttpStatus.NOT_FOUND));
    }

    // Actualizar proyecto
    @PostMapping("/update/{proyectId}")
    public ResponseEntity<Object> updateProyect(@PathVariable int proyectId, @RequestBody proyectDTO proyects) {
        responseDTO response = proyectService.update(proyectId, proyects);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    // Eliminar proyecto por ID
    @DeleteMapping("/delete/{proyectId}")
    public ResponseEntity<Object> deleteProyect(@PathVariable int proyectId) {
        responseDTO response = proyectService.deleteProyects(proyectId);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }
}