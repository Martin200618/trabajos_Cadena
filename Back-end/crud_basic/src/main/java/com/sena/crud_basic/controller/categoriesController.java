package com.sena.crud_basic.controller;

import org.springframework.web.bind.annotation.RestController;
import com.sena.crud_basic.DTO.categoriesDTO;
import com.sena.crud_basic.service.categoriesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/v4/categories")
public class categoriesController {
    @Autowired
    private categoriesService categoriesService;
    @PostMapping("/")
    public ResponseEntity<Object> registerCategories(@RequestBody categoriesDTO categories){
        categoriesService.save(categories);
        return new ResponseEntity<>("Register ok", HttpStatus.OK);
    }
}
