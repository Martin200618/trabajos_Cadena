package com.sena.crud_basic.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.DTO.Activity_proyectDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.service.Activity_proyectService;
import org.springframework.web.bind.annotation.RequestMapping;


@CrossOrigin(origins = "http://localhost:5500")
@RestController
@RequestMapping("api/v1/activity_proyects")
public class Activity_proyectController {
    @Autowired
    private Activity_proyectService activity_proyectService;

    @PostMapping("/")
    public ResponseEntity<Object> registerActivity_proyect(@RequestBody Activity_proyectDTO Activity_proyect){
        responseDTO response = activity_proyectService.save(Activity_proyect);
        return new ResponseEntity<>(
            response,
            HttpStatus.valueOf(response.getStatus())
        );
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllActivity_proyects(){
        return new ResponseEntity<>(
            activity_proyectService.findAll(),
            HttpStatus.OK
        );
    }

    @GetMapping("/get/{activity_proyectId}")
    public ResponseEntity<Object> getOneActivity_proyect(@RequestParam int activity_proyectId){
        Optional<Object> activity_proyect = Optional.ofNullable(activity_proyectService.findById(activity_proyectId));
        return activity_proyect.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>("Activity_proyect not found", HttpStatus.NOT_FOUND));
    }

    @PostMapping("/update/{activity_proyectId}")
    public ResponseEntity<Object> updateActivity_proyect(@RequestParam int activity_proyectId, @RequestBody Activity_proyectDTO Activity_proyect){
        responseDTO response = activity_proyectService.update(activity_proyectId, Activity_proyect);
        return new ResponseEntity<>(
            response,
            HttpStatus.valueOf(response.getStatus())
        );
    }

    @DeleteMapping("/delete/{activity_proyectId}")
    public ResponseEntity<Object> deleteActivity_proyect(@RequestParam int activity_proyectId){
        responseDTO response = activity_proyectService.deleteActivity_proyect(activity_proyectId);
        return new ResponseEntity<>(
            response,
            HttpStatus.valueOf(response.getStatus())
        );
    }
}