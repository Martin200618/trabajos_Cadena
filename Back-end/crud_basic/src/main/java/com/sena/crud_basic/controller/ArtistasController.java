package com.sena.crud_basic.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sena.crud_basic.DTO.ArtistasDTO;
import com.sena.crud_basic.service.ArtistasService;


@CrossOrigin(origins = "http://localhost:5500")
@RestController
@RequestMapping("api/v1/artistas")
public class ArtistasController {

    @Autowired
    private ArtistasService artistasService;

    @PostMapping("/")
    public ResponseEntity<Object> registerArtista(@RequestBody ArtistasDTO artista) {
        String response = artistasService.save(artista);
        return new ResponseEntity<>(
            response,
            response.startsWith("200") ? HttpStatus.OK : HttpStatus.BAD_REQUEST
        );
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllArtistas() {
        return new ResponseEntity<>(
            artistasService.findAll(),
            HttpStatus.OK
        );
    }

    @GetMapping("/get/{artistaId}")
    public ResponseEntity<Object> getOneArtista(@PathVariable int artistaId) {
        Optional<Object> artista = Optional.ofNullable(artistasService.findById(artistaId));
        return artista.map(value -> new ResponseEntity<>(
            value,
            HttpStatus.OK
        )).orElseGet(() -> new ResponseEntity<>(
            "Artista not found",
            HttpStatus.NOT_FOUND
        ));
    }

    @PostMapping("/update/{artistaId}")
    public ResponseEntity<Object> updateArtista(@PathVariable int artistaId, @RequestBody ArtistasDTO artista) {
        String response = artistasService.update(artistaId, artista);
        return new ResponseEntity<>(
            response,
            response.startsWith("200") ? HttpStatus.OK : HttpStatus.BAD_REQUEST
        );
    }

    @DeleteMapping("/delete/{artistaId}")
    public ResponseEntity<Object> deleteArtista(@PathVariable int artistaId) {
        String response = artistasService.delete(artistaId);
        return new ResponseEntity<>(
            response,
            response.startsWith("200") ? HttpStatus.OK : HttpStatus.BAD_REQUEST
        );
    }
}