package com.sena.crud_basic.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.sena.crud_basic.service.BranchesService;

@RestController
@RequestMapping("/api/v1/branches")
public class branchesController {
    @Autowired
    private BranchesService branchesService;
    @PostMapping("/")
    public ResponseEntity<Object> registerBranches(@RequestBody branchesDTO branches){
        branchesService.save(branches);
        return new ResponseEntity<>("Register ok", HttpStatus.ok);
    }
}