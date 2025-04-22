package com.sena.crud_basic.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.DTO.user_proyectDTO;
import com.sena.crud_basic.service.user_proyectService;

@CrossOrigin(origins = "http://localhost:5500")
@RestController
@RequestMapping("api/v1/user_proyect")
public class user_proyectController {
    @Autowired
    private user_proyectService user_proyectService;

    @PostMapping("/")
    public ResponseEntity<Object> registerUser_proyect(@RequestBody user_proyectDTO user_proyect) {
        responseDTO response = user_proyectService.save(user_proyect);
        return new ResponseEntity<>(
            response, 
            HttpStatus.valueOf(response.getStatus())
        );
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllUser_proyects() {
        return new ResponseEntity<>(
            user_proyectService.findAll(), 
            HttpStatus.OK
        );
    }

    @GetMapping("/get/{user_proyectId}")
    public ResponseEntity<Object> getOneUser_proyect(@RequestParam int user_proyectId) {
        Optional<Object> user_proyect = Optional.ofNullable(user_proyectService.findById(user_proyectId));
        return user_proyect.map(value -> new ResponseEntity<>(
            value, 
            HttpStatus.OK)
        ).orElseGet(() -> new ResponseEntity<>(
            "User_proyect not found", 
            HttpStatus.NOT_FOUND)
        );
    }

    @PostMapping("/update/{user_proyectId}")
    public ResponseEntity<Object> updateUser_proyect(@RequestParam int user_proyectId, @RequestBody user_proyectDTO user_proyect) {
        responseDTO response = user_proyectService.update(user_proyectId, user_proyect);
        return new ResponseEntity<>(
            response, 
            HttpStatus.valueOf(response.getStatus())
        );
    }

    @DeleteMapping("/delete/{user_proyectId}")
    public ResponseEntity<Object> deleteUser_proyect(@RequestParam int user_proyectId) {
        responseDTO response = user_proyectService.delete(user_proyectId);
        return new ResponseEntity<>(
            response, 
            HttpStatus.valueOf(response.getStatus())
        );
    }
}