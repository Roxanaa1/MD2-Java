package com.example.learning.controller;

import com.example.learning.entities.User;
import com.example.learning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController  //rest este un stil arhitectural
public class UserController {
    //vine din exterior instanta
    //bean-un object manage uit de SpringBoot
    //mereu se foloseste de memoria stack
    @Autowired//se injecteaza userService
    private UserService userService;

    private ResponseEntity<?> cretaeUser(User user){
        userService.create(user);
        return ResponseEntity.ok(null);
    }
}
