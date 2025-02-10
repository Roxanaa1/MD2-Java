package com.example.learning.dto;

import com.example.learning.entities.Car;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private Integer age;

    private List<BookDTO> books;
    private List<ApplicationDTO> applications;
    private List<CarDTO> cars;

}
