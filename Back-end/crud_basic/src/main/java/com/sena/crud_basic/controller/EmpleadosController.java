package com.sena.crud_basic.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sena.crud_basic.DTO.EmpleadosDTO;
import com.sena.crud_basic.service.EmpleadosService;


@CrossOrigin(origins = "http://localhost:5500")
@RestController
@RequestMapping("api/v1/empleados")
public class EmpleadosController {

    @Autowired
    private EmpleadosService empleadosService;

    @PostMapping("/")
    public ResponseEntity<Object> registerEmpleado(@RequestBody EmpleadosDTO empleado) {
        String response = empleadosService.save(empleado);
        return new ResponseEntity<>(
            response,
            response.startsWith("200") ? HttpStatus.OK : HttpStatus.BAD_REQUEST
        );
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllEmpleados() {
        return new ResponseEntity<>(
            empleadosService.findAll(),
            HttpStatus.OK
        );
    }

    @GetMapping("/get/{empleadoId}")
    public ResponseEntity<Object> getOneEmpleado(@PathVariable int empleadoId) {
        Optional<Object> empleado = Optional.ofNullable(empleadosService.findById(empleadoId));
        return empleado.map(value -> new ResponseEntity<>(
            value,
            HttpStatus.OK
        )).orElseGet(() -> new ResponseEntity<>(
            "Empleado not found",
            HttpStatus.NOT_FOUND
        ));
    }

    @PostMapping("/update/{empleadoId}")
    public ResponseEntity<Object> updateEmpleado(@PathVariable int empleadoId, @RequestBody EmpleadosDTO empleado) {
        String response = empleadosService.update(empleadoId, empleado);
        return new ResponseEntity<>(
            response,
            response.startsWith("200") ? HttpStatus.OK : HttpStatus.BAD_REQUEST
        );
    }

    @DeleteMapping("/delete/{empleadoId}")
    public ResponseEntity<Object> deleteEmpleado(@PathVariable int empleadoId) {
        String response = empleadosService.delete(empleadoId);
        return new ResponseEntity<>(
            response,
            response.startsWith("200") ? HttpStatus.OK : HttpStatus.BAD_REQUEST
        );
    }
}