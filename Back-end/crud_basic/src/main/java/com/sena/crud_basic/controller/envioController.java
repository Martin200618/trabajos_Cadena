package com.sena.crud_basic.controller;

import org.springframework.web.bind.annotation.RestController;
import com.sena.crud_basic.DTO.envioDTO;
import com.sena.crud_basic.service.envioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/v4/envio")
public class envioController {
    @Autowired
    private envioService.save(envioDTO);
    @PostMapping("/")
    public ResponseEntity<Object> registerEnvio(@RequestBody envioDTO envioDTO){
        envioService.save(envioDTO);
        return new ResponseEntity<>("Register ok", HttpStatus.OK);
    }
}
