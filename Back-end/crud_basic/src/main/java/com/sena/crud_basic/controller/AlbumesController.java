package com.sena.crud_basic.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sena.crud_basic.DTO.AlbumesDTO;
import com.sena.crud_basic.service.AlbumesService;

@CrossOrigin(origins = { "http://localhost:5500", "http://127.0.0.1:5500" }) // Se agregaron variantes
@RestController
@RequestMapping("api/v1/albumes")
public class AlbumesController {

    @Autowired
    private AlbumesService albumesService;

    // Crear un nuevo álbum
    @PostMapping("/")
    public ResponseEntity<Object> registerAlbum(@RequestBody AlbumesDTO album) {
        if (album == null || album.getTitulo() == null || album.getArtista() == null) {
            return new ResponseEntity<>("Datos incompletos", HttpStatus.BAD_REQUEST);
        }
        String response = albumesService.save(album);
        if (response.startsWith("200")) {
            return new ResponseEntity<>("Álbum registrado exitosamente", HttpStatus.OK);
        }
        return new ResponseEntity<>("Error al registrar el álbum", HttpStatus.BAD_REQUEST);
    }

    // Obtener todos los álbumes
    @GetMapping("/")
    public ResponseEntity<Object> getAllAlbums() {
        return new ResponseEntity<>(albumesService.findAll(), HttpStatus.OK);
    }

    // Obtener un álbum específico por ID
    @GetMapping("/get/{albumId}")
    public ResponseEntity<Object> getOneAlbum(@PathVariable int albumId) {
        Optional<Object> album = Optional.ofNullable(albumesService.findById(albumId));
        return album.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>("Álbum no encontrado", HttpStatus.NOT_FOUND));
    }

    // Actualizar un álbum
    @PostMapping("/update/{albumId}")
    public ResponseEntity<Object> updateAlbum(@PathVariable int albumId, @RequestBody AlbumesDTO album) {
        if (album == null || album.getTitulo() == null || album.getArtista() == null) {
            return new ResponseEntity<>("Datos incompletos para actualización", HttpStatus.BAD_REQUEST);
        }
        String response = albumesService.update(albumId, album);
        if (response.startsWith("200")) {
            return new ResponseEntity<>("Álbum actualizado exitosamente", HttpStatus.OK);
        }
        return new ResponseEntity<>("Error al actualizar el álbum", HttpStatus.BAD_REQUEST);
    }

    // Eliminar un álbum
    @DeleteMapping("/delete/{albumId}")
    public ResponseEntity<Object> deleteAlbum(@PathVariable int albumId) {
        String response = albumesService.delete(albumId);
        if (response.startsWith("200")) {
            return new ResponseEntity<>("Álbum eliminado exitosamente", HttpStatus.OK);
        }
        return new ResponseEntity<>("Error al eliminar el álbum", HttpStatus.BAD_REQUEST);
    }
}