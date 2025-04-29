package com.sena.crud_basic.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sena.crud_basic.DTO.ArtistasDTO;
import com.sena.crud_basic.service.ArtistasService;

@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"}) // Asegura solicitudes de ambos orígenes
@RestController
@RequestMapping("api/v1/artistas")
public class ArtistasController {

    @Autowired
    private ArtistasService artistasService;

    // Registrar un nuevo artista
    @PostMapping("/")
    public ResponseEntity<Object> registerArtista(@RequestBody ArtistasDTO artista) {
        String response = artistasService.save(artista);
        boolean isSuccessful = response.startsWith("200");
    
        // Respuesta estructurada
        return new ResponseEntity<>(
            Map.of(
                "message", response,
                "status", isSuccessful ? "success" : "error"
            ),
            isSuccessful ? HttpStatus.OK : HttpStatus.BAD_REQUEST
        );
    }

    // Obtener todos los artistas
    @GetMapping("/")
    public ResponseEntity<Object> getAllArtistas() {
        try {
            return new ResponseEntity<>(
                artistasService.findAll(),
                HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                Map.of("message", "Error al obtener los artistas", "error", e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    // Obtener un único artista por ID
    @GetMapping("/get/{Artistas_id}")
    public ResponseEntity<Object> getOneArtista(@PathVariable("Artistas_id") int Artistas_id) {
        Optional<Object> artista = Optional.ofNullable(artistasService.findById(Artistas_id));
        return artista.map(value -> new ResponseEntity<>(
            value,
            HttpStatus.OK
        )).orElseGet(() -> new ResponseEntity<>(
            Map.of("message", "Artista no encontrado"),
            HttpStatus.NOT_FOUND
        ));
    }

    // Actualizar un artista por ID
    @PostMapping("/update/{Artistas_id}")
    public ResponseEntity<Object> updateArtista(@PathVariable("Artistas_id") int Artistas_id, @RequestBody ArtistasDTO artista) {
        try {
            String response = artistasService.update(Artistas_id, artista);
            return new ResponseEntity<>(
                Map.of(
                    "message", response,
                    "status", response.startsWith("200") ? "success" : "error"
                ),
                response.startsWith("200") ? HttpStatus.OK : HttpStatus.BAD_REQUEST
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                Map.of("message", "Error al actualizar el artista", "error", e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    // Eliminar un artista por ID
    @DeleteMapping("/delete/{Artistas_id}")
    public ResponseEntity<Object> deleteArtista(@PathVariable("Artistas_id") int Artistas_id) {
        try {
            String response = artistasService.delete(Artistas_id);
            return new ResponseEntity<>(
                Map.of(
                    "message", response,
                    "status", response.startsWith("200") ? "success" : "error"
                ),
                response.startsWith("200") ? HttpStatus.OK : HttpStatus.BAD_REQUEST
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                Map.of("message", "Error al eliminar el artista", "error", e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}