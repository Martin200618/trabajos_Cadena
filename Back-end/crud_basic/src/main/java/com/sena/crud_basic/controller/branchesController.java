package com.sena.crud_basic.controller;

import org.springframework.web.bind.annotation.RestController;
import com.sena.crud_basic.DTO.branchesDTO;
import com.sena.crud_basic.service.branchesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/v1/branches")
public class branchesController {
    @Autowired
    private branchesService branchesService;
    @PostMapping("/")
    public ResponseEntity<Object> registerBranches(@RequestBody branchesDTO branches){
        branchesService.save(branches);
        return new ResponseEntity<>("Register ok", HttpStatus.OK);
    }
}