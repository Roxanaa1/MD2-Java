package com.example.learning.dto.mapper;

import com.example.learning.dto.UserDTO;
import com.example.learning.entities.Application;
import com.example.learning.entities.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {

    public  User toEntity(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        User user = new User();
        user.setUserName(userDTO.getUserName());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setAge(userDTO.getAge());
        List<Application> applications = userDTO.getApplications().stream()
//                .map(applicationDto -> ApplicationMapper.applicationDTO2Application(applicationDto))
                .map(ApplicationMapper::applicationDTO2Application)
                .toList();
        user.setApplications(applications);
        return user;
    }

    public  UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUserName(user.getUserName());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setAge(user.getAge());
        userDTO.setApplications(user.getApplications().stream()
                .map(ApplicationMapper::application2ApplicationDTO)
                .toList());
        //ideal: trebuia sa fie si books aici
        return userDTO;
    }
}
