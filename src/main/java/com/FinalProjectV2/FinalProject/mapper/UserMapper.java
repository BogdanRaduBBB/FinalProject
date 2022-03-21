package com.FinalProjectV2.FinalProject.mapper;

import com.FinalProjectV2.FinalProject.entity.Role;
import com.FinalProjectV2.FinalProject.entity.User;
import com.FinalProjectV2.FinalProject.dto.UserDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class UserMapper {



    public UserDTO fromEntityToDto(User user){
        return new UserDTO(user.getEmail(), user.getUserName(), user.getPassword(), user.getFirstName(), user.getLastName());
    }

    public User fromDtoToEntity(UserDTO user){
        return new User(user.getId(), user.getEmail(), user.getUserName(), user.getPassword(), user.getFirstName(), user.getLastName(),user.getActive(), Arrays.asList(new Role("ROLE_USER")));
    }
}
