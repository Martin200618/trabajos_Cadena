package com.sena.crud_basic.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.DTO.team_userDTO;
import com.sena.crud_basic.service.team_userService;

@CrossOrigin(origins = "http://localhost:5500")
@RestController
@RequestMapping("api/v1/team_user")
public class team_userController {
    @Autowired
    private team_userService team_userService;

    @PostMapping("/")
    public ResponseEntity<Object> registerTeam_user(@RequestBody team_userDTO team_user) {
        responseDTO response = team_userService.save(team_user);
        return new ResponseEntity<>(
            response, 
            HttpStatus.valueOf(response.getStatus())
        );
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllTeam_user() {
        return new ResponseEntity<>(
            team_userService.findAll(),
            HttpStatus.OK
        );
    }

    @GetMapping("/get/{team_userId}")
    public ResponseEntity<Object> getOneTeam_user(@RequestBody int team_userId) {
        Optional<Object> team_user = Optional.ofNullable(team_userService.findById(team_userId));
        return team_user.map(value -> new ResponseEntity<>(
            value, 
            HttpStatus.OK)
        ).orElseGet(() -> new ResponseEntity<>(
            "Team_user not found", 
            HttpStatus.NOT_FOUND)
        );
    }

    @PostMapping("/update/{team_userId}")
    public ResponseEntity<Object> updateTeam_user(@RequestBody int team_userId, @RequestBody team_userDTO team_user) {
        responseDTO response = team_userService.updateUnion(team_userId, team_user);
        return new ResponseEntity<>(
            response, 
            HttpStatus.valueOf(response.getStatus())
        );
    }

    @DeleteMapping("/delete/{team_userId}")
    public ResponseEntity<Object> deleteUnion(@RequestBody int team_userId) {
        responseDTO response = team_userService.deleteUnion(team_userId);
        return new ResponseEntity<>(
            response, 
            HttpStatus.valueOf(response.getStatus())
        );
    }
}