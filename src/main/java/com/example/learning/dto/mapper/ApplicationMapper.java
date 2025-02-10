package com.example.learning.dto.mapper;

import com.example.learning.dto.ApplicationDTO;
import com.example.learning.entities.Application;
import org.springframework.stereotype.Component;

@Component
public class ApplicationMapper {
    public static Application applicationDTO2Application(ApplicationDTO applicationDTO) {
        Application application = new Application();
        application.setName(applicationDTO.getName());
        return application;
    }

    public static ApplicationDTO application2ApplicationDTO(Application application) {
        ApplicationDTO applicationDTO = new ApplicationDTO();
        applicationDTO.setId(application.getId());
        applicationDTO.setName(application.getName());
        return applicationDTO;
    }
}
