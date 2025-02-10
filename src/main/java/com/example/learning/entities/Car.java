package com.example.learning.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "cars")
@Table(name = "CARS")
@Getter
@Setter
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "BRAND")
    private String brand;
    @Column(name = "MODEL")

    private String model;
    @Column(name = "COLOR")

    private String color;
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;


}
