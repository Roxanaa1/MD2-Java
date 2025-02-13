package com.example.learning.controller;

import com.example.learning.dto.UserDTO;
import com.example.learning.entities.Car;
import com.example.learning.entities.User;
import com.example.learning.dto.mapper.UserMapper;
import com.example.learning.service.CarService;
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
    @Autowired
    private CarService carService;

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

    @PostMapping("/with-apps")
    public ResponseEntity<?> createWithApplications(@RequestBody UserDTO userDTO) {
        User userToCreate = userMapper.toEntity(userDTO);
        User createdUser = userService.create(userToCreate);
        return ResponseEntity.ok(userMapper.toDTO(createdUser));
    }

    /*
    create parent with children (1)
    create parent without children (1
    * */
    @PostMapping("/with-cars")
    public ResponseEntity<?> createWithCars(@RequestBody UserDTO userDTO) {
        User userEntity = userMapper.toEntity(userDTO);
        User createdUser = userService.create(userEntity);
        UserDTO createdUserDTO = userMapper.toDTO(createdUser);
        return ResponseEntity.ok(createdUserDTO);
    }

    //add existing child to parent (3)
    @PostMapping("/add-car-to-user/{userId}/{carId}")
    public ResponseEntity<?> addExistingCarToUser(@PathVariable Long userId, @PathVariable Long carId) {
        User user = userService.getById(userId);
        Car car = carService.getById(carId);
        user.addCar(car);
        userService.updateUser(user, userId);
        return ResponseEntity.ok(userMapper.toDTO(user));
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
        List<UserDTO> foundUsersDTO = users.stream().map(user -> userMapper.toDTO(user)).toList();
        return ResponseEntity.ok(foundUsersDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestBody UserDTO userDTO, @PathVariable Long id) {
        User userEntity = userMapper.toEntity(userDTO);
        User userUpdate = userService.updateUser(userEntity, id);
        UserDTO updatedUserDTO = userMapper.toDTO(userUpdate);

        return ResponseEntity.ok(updatedUserDTO);
    }

    //delete parent should delete child (cascade type remove)
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId); // `CascadeType.REMOVE`
        return ResponseEntity.ok().build();// status 200 ok(un raspuns fara corp)
    }

    //remove child from parent (test orphan removal)
//    @DeleteMapping("/remove-car-from-user/{userId}/{carId}")
//    public ResponseEntity<?> removeCarFromUser(@PathVariable Long userId, @PathVariable Long carId) {
//        User user = userService.getById(userId);
//        user.getCars().removeIf(car -> car.getId().equals(carId));
//        userService.updateUser(user, userId);
//        return ResponseEntity.ok(userMapper.toDTO(user));
//    }

}
