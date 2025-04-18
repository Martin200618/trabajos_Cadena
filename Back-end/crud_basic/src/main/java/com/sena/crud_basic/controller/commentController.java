package com.sena.crud_basic.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sena.crud_basic.DTO.commentDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.service.commentService;

@CrossOrigin(origins = "http://localhost:5500")
@RestController
@RequestMapping("api/v1/comments")
public class commentController {
    @Autowired
    private commentService commentService;

    @PostMapping("/")
    public ResponseEntity<Object> registerComment(@RequestBody commentDTO comment) {
        responseDTO response = commentService.save(comment);
        return new ResponseEntity<>(
            response,
            HttpStatus.valueOf(response.getStatus())
        );
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllComments() {
        return new ResponseEntity<>(
            commentService.findAll(),
            HttpStatus.OK
        );
    }

    @GetMapping("/get/{commentId}")
    public ResponseEntity<Object> getOneComment(@RequestParam int commentId){
        Optional<Object> comment = Optional.ofNullable(commentService.findById(commentId));
        return comment.map(value -> new ResponseEntity<>(
            value, 
            HttpStatus.OK)
        ).orElseGet(() -> new ResponseEntity<>(
            "Comment not found", 
            HttpStatus.NOT_FOUND)
        );
    }

    @PostMapping("/update/{commentId}")
    public ResponseEntity<Object> updateComment(@RequestParam int commentId, @RequestBody commentDTO comment) {
        responseDTO response = commentService.updateComment(commentId, comment);
        return new ResponseEntity<>(
            response,
            HttpStatus.valueOf(response.getStatus())
        );
    }

    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<Object> deleteComment(@RequestParam int commentId) {
        responseDTO response = commentService.deleteComment(commentId);
        return new ResponseEntity<>(
            response,
            HttpStatus.valueOf(response.getStatus())
        );
    }
}