package com.FinalProjectV2.FinalProject.Controller;

import com.FinalProjectV2.FinalProject.Service.UserService;
import com.FinalProjectV2.FinalProject.dto.UserDTO;
import com.FinalProjectV2.FinalProject.entity.User;
//import com.FinalProjectV2.FinalProject.mapper.UserMapper;
import com.FinalProjectV2.FinalProject.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    @Autowired
    UserMapper mapper;

    @Autowired
    private UserService service;

    @ModelAttribute("user")
    public UserDTO userRegistrationDto() {
        return new UserDTO();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserDTO user) {
        service.save(user);
        return "redirect:/registration?success";
    }

}
