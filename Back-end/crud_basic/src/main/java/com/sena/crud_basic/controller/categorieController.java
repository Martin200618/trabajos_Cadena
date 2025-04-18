package com.sena.crud_basic.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.sena.crud_basic.DTO.categorieDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.service.categorieService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5500")
@RestController
@RequestMapping("api/v1/categories")
public class categorieController {
    @Autowired
    private categorieService categorieService;

    @PostMapping("/")
    public ResponseEntity<Object> registerCategorie(@RequestBody categorieDTO categorie) {
        responseDTO response = categorieService.save(categorie);
        return new ResponseEntity<>(
            response,
            HttpStatus.valueOf(response.getStatus())
        );
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllCategories() {
        return new ResponseEntity<>(
            categorieService.findAll(),
            HttpStatus.OK
        );
    }

    @GetMapping("/get/{categorieId}")
    public ResponseEntity<Object> getOneCategorie(@RequestParam int categorieId){
        Optional<Object> categorie = Optional.ofNullable(categorieService.findById(categorieId));
        return categorie.map(value -> new ResponseEntity<>(
            value, 
            HttpStatus.OK)
        ).orElseGet(() -> new ResponseEntity<>(
            "Categorie not found", 
            HttpStatus.NOT_FOUND)
        );
    }

    @PostMapping("/update/{categorieId}")
    public ResponseEntity<Object> updateCategorie(@RequestParam int categorieId, @RequestBody categorieDTO categorie) {
        responseDTO response = categorieService.updateCategorie(categorieId, categorie);
        return new ResponseEntity<>(
            response,
            HttpStatus.valueOf(response.getStatus())
        );
    }

    @DeleteMapping("/delete/{categorieId}")
    public ResponseEntity<Object> deleteCategorie(@RequestParam int categorieId) {
        responseDTO response = categorieService.deleteCategorie(categorieId);
        return new ResponseEntity<>(
            response,
            HttpStatus.valueOf(response.getStatus())
        );
    }
}