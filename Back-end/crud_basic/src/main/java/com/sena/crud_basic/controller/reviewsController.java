package com.sena.crud_basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.DTO.reviewsDTO;
import com.sena.crud_basic.service.reviewsService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/reviews")
public class reviewsController {
    @Autowired
    private reviewsService reviewsService;

    @PostMapping("/")
    public ResponseEntity<Object> registrarReviews(@RequestBody reviewsDTO reviews){
        responseDTO respuesta = reviewsService.save(reviews);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllReviews(){
        var listareviews = reviewsService.findAll();
        return new ResponseEntity<>(listareviews, HttpStatus.OK);
    }

    @GetMapping("/get/{reviewsId}")
    public ResponseEntity<Object> getOneReviews(@PathVariable int reviewsId){
        var reviews = reviewsService.findById(reviewsId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{reviewsId}")
    public ResponseEntity<Object> deleteReviews(@PathVariable int reviewsId){
        var reviews = reviewsService.findById(reviewsId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @PutMapping("/update/{reviewsId}")
    public ResponseEntity<Object> updateReviews(@PathVariable int reviewsId, @RequestBody reviewsDTO reviews){
        responseDTO updatedReviews = reviewsService.update(reviewsId, reviews);
        if (updatedReviews!= null) {
            return new ResponseEntity<>(updatedReviews, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new responseDTO("Error", "No se pudo actualizar el comentario"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}