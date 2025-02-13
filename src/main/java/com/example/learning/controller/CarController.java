package com.example.learning.controller;

import com.example.learning.dto.CarDTO;
import com.example.learning.dto.mapper.CarMapper;
import com.example.learning.entities.Car;
import com.example.learning.entities.User;
import com.example.learning.service.CarService;
import com.example.learning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private UserService userService;
    @Autowired
    private CarService carService;

    //create children (2)
    @PostMapping("/{userId}")
    public ResponseEntity<?> create(@PathVariable Long userId, @RequestBody CarDTO carDTO) {
        Car carToCreate = CarMapper.carDtoToCarEntity(carDTO);
        Car createdCar = carService.create(carToCreate, userId);
        CarDTO createdCarDTO = CarMapper.carEntityToCarDto(createdCar);
        return ResponseEntity.ok(createdCarDTO);
    }

    // create child with parent id as param (4)

    @PostMapping("/create-car/{userId}")
    public ResponseEntity<?> createCarForUser(@PathVariable Long userId, @RequestBody CarDTO carDTO) {
        Car carToCreate = CarMapper.carDtoToCarEntity(carDTO);
        User user = userService.getById(userId);
        user.addCar(carToCreate);
        userService.updateUser(user, userId);
        return ResponseEntity.ok(carDTO);
    }

}
