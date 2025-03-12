package com.sena.crud_basic.controller;

import org.springframework.web.bind.annotation.RestController;
import com.sena.crud_basic.DTO.meto_pagoDTO;
import com.sena.crud_basic.service.meto_pagoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/v4/metodos_pago")
public class meto_pagoController {
    @Autowired
    private meto_pagoService meto_pagoService;
    @PostMapping("/")
    public ResponseEntity<Object> registermeto_pago(@RequestBody meto_pagoDTO meto_pagoDTO){
        meto_pagoService.save(meto_pagoDTO);
        return new ResponseEntity<>("Register ok",HttpStatus.OK);
    }
    
}