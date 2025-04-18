package com.sena.crud_basic.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.DTO.workDTO;
import com.sena.crud_basic.service.workService;

@CrossOrigin(origins = "http://localhost:5500")
@RestController
@RequestMapping("api/v1/works")
public class workController {
    @Autowired
    private workService workService;

    @PostMapping("/")
    public ResponseEntity<Object> registerWork(@RequestBody workDTO work){
        responseDTO response = workService.save(work);
        return new ResponseEntity<>(
            response, 
            HttpStatus.valueOf(response.getStatus())
        );
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAll(){
        return new ResponseEntity<>(
            workService.findAll(), 
            HttpStatus.OK
        );
    }

    @GetMapping("/get/{workId}")
    public ResponseEntity<Object> getOneWork(@RequestBody int workId){
        Optional<Object> work = Optional.ofNullable(workService.findById(workId));
        return work.map(value -> new ResponseEntity<>(
            value, 
            HttpStatus.OK)
        ).orElseGet(() -> new ResponseEntity<>(
            "Work not found", 
            HttpStatus.NOT_FOUND)
        );
    }

    @PostMapping("/update/{workId}")
    public ResponseEntity<Object> updateWork(@RequestBody int workId, @RequestBody workDTO work){
        responseDTO response = workService.update(workId, work);
        return new ResponseEntity<>(
            response, 
            HttpStatus.valueOf(response.getStatus())
        );
    }

    @DeleteMapping("/delete/{workId}")
    public ResponseEntity<Object> deleteWork(@RequestBody int workId){
        responseDTO response = workService.deleteWork(workId);
        return new ResponseEntity<>(
            response, 
            HttpStatus.valueOf(response.getStatus())
        );
    }
}