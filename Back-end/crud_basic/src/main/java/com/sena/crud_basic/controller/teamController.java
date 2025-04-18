package com.sena.crud_basic.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.DTO.teamsDTO;
import com.sena.crud_basic.service.teamsService;

@CrossOrigin(origins = "http://localhost:5500")
@RestController
@RequestMapping("api/v1/teams")
public class teamController {
    @Autowired
    private teamsService teamsService;

    @PostMapping("/")
    public ResponseEntity<Object> registerTeam(@RequestBody teamsDTO teams) {
        responseDTO response = teamsService.save(teams);
        return new ResponseEntity<>(
            response, 
            HttpStatus.valueOf(response.getStatus())
        );
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllTeams() {
        return new ResponseEntity<>(
            teamsService.findAll(), 
            HttpStatus.OK
        );
    }

    @GetMapping("/get/{teamId}")
    public ResponseEntity<Object> getOneTeam(@RequestParam int teamId){
        Optional<Object> team = Optional.ofNullable(teamsService.findById(teamId));
        return team.map(value -> new ResponseEntity<>(
            value, 
            HttpStatus.OK)
        ).orElseGet(() -> new ResponseEntity<>(
            "Team not found", 
            HttpStatus.NOT_FOUND)
        );
    }

    @PostMapping("/update/{teamId}")
    public ResponseEntity<Object> updateTeam(@RequestParam int teamId, @RequestBody teamsDTO teams) {
        responseDTO response = teamsService.update(teamId, teams);
        return new ResponseEntity<>(
            response, 
            HttpStatus.valueOf(response.getStatus())
        );
    }

    @PostMapping("/delete/{teamId}")
    public ResponseEntity<Object> deleteTeam(@RequestParam int teamId) {
        responseDTO response = teamsService.deleteTeam(teamId);
        return new ResponseEntity<>(
            response, 
            HttpStatus.valueOf(response.getStatus())
        );
    }
}