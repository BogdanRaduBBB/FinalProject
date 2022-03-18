package com.FinalProjectV2.FinalProject.Controller;

import com.FinalProjectV2.FinalProject.Service.UserService;
import com.FinalProjectV2.FinalProject.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping("/register")
    public String processRegister(@RequestBody User user) {
        service.registerDefaultUser(user);

        return "register_success";
    }
}
