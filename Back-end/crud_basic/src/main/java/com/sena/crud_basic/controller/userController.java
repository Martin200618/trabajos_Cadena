package com.sena.crud_basic.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.DTO.userDTO;
import com.sena.crud_basic.service.userService;

@CrossOrigin(origins = "http://localhost:5500")
@RestController
@RequestMapping("/api/v1/user")
public class userController {
    @Autowired
    private userService userService;

    // Registro de usuario
    @PostMapping
    public ResponseEntity<Object> registerUser(@RequestBody userDTO user) {
        responseDTO response = userService.save(user);
        if (response.getStatus().equals(HttpStatus.BAD_REQUEST.toString())) {
            return new ResponseEntity<>(response.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response.getMessage(), HttpStatus.OK);
    }

    // Obtener todos los usuarios
    @GetMapping("/")
    public ResponseEntity<Object> getAllUser() {
        var userList = userService.findAll();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    // Login de usuario
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        responseDTO response = userService.login(email, password);
        Map<String, String> responseBody = Map.of("message", response.getMessage());

        if (response.getStatus().equals(HttpStatus.UNAUTHORIZED.toString())) {
            return new ResponseEntity<>(responseBody, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    // Obtener un usuario por ID
    @GetMapping("/get/{user_id}")
    public ResponseEntity<Object> getOneUser(@PathVariable int user_id) {
        var user = userService.findById(user_id);
        if (!user.isPresent()) {
            return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // Eliminar un usuario por ID
    @DeleteMapping("/delete/{user_id}")
    public ResponseEntity<Object> deleteUser(@PathVariable int user_id) {
        var message = userService.deleteUser(user_id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    // Actualizar un usuario por ID
    @PostMapping("/update/{user_id}")
    public ResponseEntity<Object> updateUser(@PathVariable int user_id, @RequestBody userDTO user) {
        var existingUser = userService.findById(user_id);
        if (!existingUser.isPresent()) {
            return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
        }
        responseDTO response = userService.update(user_id, user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}