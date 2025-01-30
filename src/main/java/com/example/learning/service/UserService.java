package com.example.learning.service;

import com.example.learning.entities.User;
import com.example.learning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {
    @Autowired
    private UserRepository userRepository;


    public void create(User user){

    }
}
