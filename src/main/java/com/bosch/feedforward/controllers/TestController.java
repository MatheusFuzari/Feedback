package com.bosch.feedforward.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/teste")
@CrossOrigin("*")
public class TestController {

    @GetMapping("/user")
    public ResponseEntity<String> tryUser(){
        return ResponseEntity.status(HttpStatus.OK).body("Hello User");
    }

    @GetMapping("/admin")
    public ResponseEntity<String> tryAdmin(){
        return ResponseEntity.status(HttpStatus.OK).body("Hello Admin");
    }
}
