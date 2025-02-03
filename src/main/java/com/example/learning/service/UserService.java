package com.example.learning.service;

import com.example.learning.entities.User;
import com.example.learning.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Component clasa de business logic(un serviciu
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User create(User user) {
        if (user.getId() != null) {
            throw new RuntimeException("You cannot provide an ID to a new user that you want to create");
        }
        return userRepository.save(user);
    }

    public User getById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void deleteUser(Long userId){
        userRepository.deleteById(userId);
    }

    public User updateUser(User user,Long id){
        return userRepository.findById(id).map(userMap ->
        {
            userMap.setUserName(user.getUserName());
            userMap.setFirstName(user.getFirstName());
            userMap.setLastName(user.getLastName());
            userMap.setAge(user.getAge());

            return userRepository.save(user);

        }).orElseThrow(()-> new EntityNotFoundException("User not found with id:"+id));
    }
}
