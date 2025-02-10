package com.example.learning.dto.mapper;

import com.example.learning.dto.CarDTO;
import com.example.learning.entities.Car;
import org.springframework.stereotype.Component;

@Component
public class CarMapper {

    public static Car carDtoToCarEntity(CarDTO carDTO){
        Car car=new Car();
        car.setBrand(carDTO.getBrand());
        car.setModel(carDTO.getModel());
        car.setColor(carDTO.getColor());
        return car;
    }

    public static CarDTO carEntityToCarDto(Car car){
        CarDTO carDTO=new CarDTO();

        carDTO.setId(car.getId());
        carDTO.setBrand(car.getBrand());
        carDTO.setModel(car.getModel());
        carDTO.setColor(car.getColor());
        return carDTO;
    }
}
