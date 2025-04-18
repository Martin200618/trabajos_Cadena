package com.sena.crud_basic.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.sena.crud_basic.DTO.ActivityDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.service.ActivityService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5500")
@RestController
@RequestMapping("api/v1/activities")
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    @PostMapping("/")
    public ResponseEntity<Object> registerActivity(@RequestBody ActivityDTO activity) {
        responseDTO response = activityService.save(activity);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllActivities() {
        return new ResponseEntity<>(
            activityService.findAll(), 
            HttpStatus.OK
        );
    }

    @GetMapping("/get/{activityId}")
    public ResponseEntity<Object> getOneActivity(@RequestParam int activityId) {
        Optional<Object> activity = Optional.ofNullable(activityService.findById(activityId));
        return activity.map(value -> new ResponseEntity<>(
            value, 
            HttpStatus.OK)
        ).orElseGet(() -> new ResponseEntity<>(
            "Activity not found", 
            HttpStatus.NOT_FOUND)
        );
    }

    @PostMapping("/update/{activityId}")
    public ResponseEntity<Object> updateActivity(@RequestParam int activityId, @RequestBody ActivityDTO activity) {
        responseDTO response = activityService.update(activityId, activity);
        return new ResponseEntity<>(
            response, 
            HttpStatus.valueOf(response.getStatus())
        );
    }

    @DeleteMapping("/delete/{activityId}")
    public ResponseEntity<Object> deleteActivity(@RequestParam int activityId) {
        responseDTO response = activityService.deleteActivity(activityId);
        return new ResponseEntity<>(
            response, 
            HttpStatus.valueOf(response.getStatus())
        );
    }
}