package com.sena.crud_basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.DTO.categoriesDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.service.categoriesService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1/categories")
public class categoriesController {
    @Autowired
    private categoriesService categoriesService;
    @PostMapping("/")
    public ResponseEntity<Object> registerCategories(@RequestBody categoriesDTO categories){
        responseDTO respuesta = categoriesService.save(categories);
        return new ResponseEntity<>(respuesta,HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<Object> getAllCategories(){
        var listaCategories = categoriesService.findAll();
        return new ResponseEntity<>(listaCategories,HttpStatus.OK);
    }

    @GetMapping("/get/{categorie_id}")
    public ResponseEntity<Object> getOneCategories(@PathVariable int categorie_id){
        var categoria = categoriesService.findById(categorie_id);
        if(!categoria.isPresent())
            return new ResponseEntity<>("",HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(categoria, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{categorie_id}")
    public ResponseEntity<Object> deletecaterie(@PathVariable int categorie_id){
        var message = categoriesService.deleteCategorie(categorie_id);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }
}