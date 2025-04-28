package com.sena.crud_basic.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sena.crud_basic.DTO.EstudiosGrabacionDTO;
import com.sena.crud_basic.service.EstudiosGrabacionService;


@CrossOrigin(origins = "http://localhost:5500")
@RestController
@RequestMapping("api/v1/estudios")
public class EstudiosGrabacionController {

    @Autowired
    private EstudiosGrabacionService estudiosService;

    @PostMapping("/")
    public ResponseEntity<Object> registerEstudio(@RequestBody EstudiosGrabacionDTO estudio) {
        String response = estudiosService.save(estudio);
        return new ResponseEntity<>(
            response,
            response.startsWith("200") ? HttpStatus.OK : HttpStatus.BAD_REQUEST
        );
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllEstudios() {
        return new ResponseEntity<>(
            estudiosService.findAll(),
            HttpStatus.OK
        );
    }

    @GetMapping("/get/{estudioId}")
    public ResponseEntity<Object> getOneEstudio(@PathVariable int estudioId) {
        Optional<Object> estudio = Optional.ofNullable(estudiosService.findById(estudioId));
        return estudio.map(value -> new ResponseEntity<>(
            value,
            HttpStatus.OK
        )).orElseGet(() -> new ResponseEntity<>(
            "Estudio not found",
            HttpStatus.NOT_FOUND
        ));
    }

    @PostMapping("/update/{estudioId}")
    public ResponseEntity<Object> updateEstudio(@PathVariable int estudioId, @RequestBody EstudiosGrabacionDTO estudio) {
        String response = estudiosService.update(estudioId, estudio);
        return new ResponseEntity<>(
            response,
            response.startsWith("200") ? HttpStatus.OK : HttpStatus.BAD_REQUEST
        );
    }

    @DeleteMapping("/delete/{estudioId}")
    public ResponseEntity<Object> deleteEstudio(@PathVariable int estudioId) {
        String response = estudiosService.delete(estudioId);
        return new ResponseEntity<>(
            response,
            response.startsWith("200") ? HttpStatus.OK : HttpStatus.BAD_REQUEST
        );
    }
}