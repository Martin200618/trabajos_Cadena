package com.sena.crud_basic.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sena.crud_basic.DTO.AlbumesDTO;
import com.sena.crud_basic.service.AlbumesService;

@CrossOrigin(origins = "http://localhost:5500")
@RestController
@RequestMapping("api/v1/albumes")
public class AlbumesController {

    @Autowired
    private AlbumesService albumesService;

    @PostMapping("/")
    public ResponseEntity<Object> registerAlbum(@RequestBody AlbumesDTO album) {
        String response = albumesService.save(album);
        return new ResponseEntity<>(
            response,
            response.startsWith("200") ? HttpStatus.OK : HttpStatus.BAD_REQUEST
        );
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllAlbums() {
        return new ResponseEntity<>(
            albumesService.findAll(),
            HttpStatus.OK
        );
    }

    @GetMapping("/get/{albumId}")
    public ResponseEntity<Object> getOneAlbum(@PathVariable int albumId) {
        Optional<Object> album = Optional.ofNullable(albumesService.findById(albumId));
        return album.map(value -> new ResponseEntity<>(
            value,
            HttpStatus.OK
        )).orElseGet(() -> new ResponseEntity<>(
            "Album not found",
            HttpStatus.NOT_FOUND
        ));
    }

    @PostMapping("/update/{albumId}")
    public ResponseEntity<Object> updateAlbum(@PathVariable int albumId, @RequestBody AlbumesDTO album) {
        String response = albumesService.update(albumId, album);
        return new ResponseEntity<>(
            response,
            response.startsWith("200") ? HttpStatus.OK : HttpStatus.BAD_REQUEST
        );
    }

    @DeleteMapping("/delete/{albumId}")
    public ResponseEntity<Object> deleteAlbum(@PathVariable int albumId) {
        String response = albumesService.delete(albumId);
        return new ResponseEntity<>(
            response,
            response.startsWith("200") ? HttpStatus.OK : HttpStatus.BAD_REQUEST
        );
    }
}