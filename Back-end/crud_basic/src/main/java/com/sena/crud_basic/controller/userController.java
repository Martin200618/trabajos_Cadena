package com.sena.crud_basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


@RestController
@RequestMapping("/api/v1/user")
public class userController {
    @Autowired
    private userService userService;
    @PostMapping("/")
    public ResponseEntity<Object> registerUser(@RequestBody userDTO user){
        responseDTO respuesta = userService.save(user);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<Object> getAllUser(){
        var ListaUsuario = userService.findAll();
        return new ResponseEntity<>(ListaUsuario,HttpStatus.OK);
    }
    @GetMapping("/get/{user_id}")
    public ResponseEntity<Object> getOneUser(@PathVariable int user_id){
        var usuario = userService.fingById(user_id);
        if(!usuario.isPresent())
            return new ResponseEntity<>("",HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(usuario,HttpStatus.OK);
    }
    @DeleteMapping("/delete/{user_id}")
    public ResponseEntity<Object> deleteUser(@PathVariable int user_id){
        var message = userService.deleteuser(user_id);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }
    @PostMapping("/update/{user_id}")
    public ResponseEntity<Object> updateUser(@PathVariable int user_id, @RequestBody userDTO user){
        var usuario = userService.fingById(user_id);
        if(!usuario.isPresent())
            return new ResponseEntity<>("",HttpStatus.NOT_FOUND);
        responseDTO respuesta = userService.update(user_id, user);
        return new ResponseEntity<>(respuesta,HttpStatus.OK);
    }
}