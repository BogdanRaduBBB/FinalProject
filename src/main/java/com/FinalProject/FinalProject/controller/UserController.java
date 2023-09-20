package com.FinalProject.FinalProject.controller;

import com.FinalProject.FinalProject.entity.Role;
import com.FinalProject.FinalProject.entity.User;
import com.FinalProject.FinalProject.repository.UserRepository;
import com.FinalProject.FinalProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService service = new UserService();

    @Autowired
    UserRepository repository;

    @RequestMapping(value="/create")
    @ResponseBody
    public String create(@RequestBody String email, String userName, String password, String firstName, String lastName, Boolean active, Role role ) {
        try {
            User user = new User(email,userName,password,firstName,lastName);
            repository.save(user);
        }
        catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
        return "User succesfully created!";
    }
}
