package com.FinalProjectV2.FinalProject.Controller;

import com.FinalProjectV2.FinalProject.Repository.UserRepository;
import com.FinalProjectV2.FinalProject.dto.UserDTO;
import com.FinalProjectV2.FinalProject.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class MainController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/login")
    public String login() {
        return ("login");
    }

    @GetMapping({"/", "/index"})
    public String showUserList(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "index";
    }
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, @Validated Model model, RedirectAttributes ra) {
        try {
            Optional<User> user = userRepository.findById(id);
            model.addAttribute("user", user);
            return "update-user";
        }
        catch (Exception e){
            ra.addFlashAttribute("message","The user has been saved successfully");
            return "redirect:/all";
        }
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id,  User user,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "update-user";
        }
        userRepository.save(user);
        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);
        return "redirect:/index";
    }

    @GetMapping("/all")
    public List<UserDTO> users() {
        return userRepository.findAll().stream()
                .map(User::convert)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserByUserName(@PathVariable String username) {
        final Optional<User> user = Optional.ofNullable(userRepository.findByUserName(username));
        if (user.isEmpty()) {
            return new ResponseEntity<>("User with username" + username + " not found", HttpStatus.NOT_FOUND);
        } else
            return new ResponseEntity<>(UserDTO.convert(user.get()), HttpStatus.FOUND);
    }
}
