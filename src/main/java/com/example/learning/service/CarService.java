package com.example.learning.service;

import com.example.learning.entities.Car;
import com.example.learning.entities.User;
import com.example.learning.repository.CarRepository;
import com.example.learning.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CarRepository carRepository;

    public Car create(Car carToCreate, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(EntityNotFoundException::new);
        user.addCar(carToCreate);
        carRepository.save(carToCreate);
        return carToCreate;
    }
}
