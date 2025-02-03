package com.example.learning.controller;

import com.example.learning.dto.UserDTO;
import com.example.learning.entities.User;
import com.example.learning.dto.mapper.UserMapper;
import com.example.learning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  //rest este un stil arhitectural
//folosim forma de plural
@RequestMapping("/users")
public class UserController {
    //vine din exterior instanta
    //bean-un object manage uit de SpringBoot
    //mereu se foloseste de memoria stack
    @Autowired//se injecteaza userService
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    //cand cream date, folosim POST
    //nu mai este nevoie de "/create", pentru ca un POST reprezinta automat crearea unei noi entitati (ca principiu)
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody UserDTO userDto) {
        //comment daca am avea parametrul ca DTO:
        //pas1: il convertesc intr-o entitate User (printr-o clasa Mapper)
        User userEntity = userMapper.toEntity(userDto);
        //pas 2: linia de mai jos
        User createdUser = userService.create(userEntity);
        //pas 3: convertesc entitatea din nou intr-un DTO
        UserDTO createdUserDTO = userMapper.toDTO(createdUser);
        return ResponseEntity.ok(createdUserDTO);
    }

    //returnam un user dupa id
    //id-ul il pun in path/cale, pentru ca un GET nu are request body (doar response body)
    @GetMapping("/{userId}")
    public ResponseEntity<?> getById(@PathVariable(name = "userId") Long userIdToSearchFor) {
        User foundUser = userService.getById(userIdToSearchFor);
        UserDTO foundUserDTO = userMapper.toDTO(foundUser);
        return ResponseEntity.ok(foundUserDTO);
    }

    @GetMapping()
    public ResponseEntity<?> findAll() {
        List<User> users = userService.findAll();
        List<UserDTO> foundUsersDTO=users.stream()
                .map(user -> userMapper.toDTO(user))
                .toList();
        return ResponseEntity.ok(foundUsersDTO);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestBody UserDTO userDTO,@PathVariable Long id ){
        User userEntity = userMapper.toEntity(userDTO);
        User userUpdate=userService.updateUser(userEntity,id);
        UserDTO updatedUserDTO = userMapper.toDTO(userUpdate);

        return ResponseEntity.ok(updatedUserDTO);
    }
}
