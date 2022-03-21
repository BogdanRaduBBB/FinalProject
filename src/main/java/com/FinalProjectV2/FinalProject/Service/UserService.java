package com.FinalProjectV2.FinalProject.Service;


import com.FinalProjectV2.FinalProject.Repository.RoleRepository;
import com.FinalProjectV2.FinalProject.Repository.UserRepository;
import com.FinalProjectV2.FinalProject.dto.UserDTO;
import com.FinalProjectV2.FinalProject.entity.Role;
import com.FinalProjectV2.FinalProject.entity.User;
//import com.FinalProjectV2.FinalProject.mapper.UserMapper;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
@Service
@SessionAttributes(value = "user")
public class UserService implements UserServiceInterface{

//    @Autowired
//    BCryptPasswordEncoder encoder;

    @Autowired
    private UserRepository userRepo;

//    @Autowired
//    UserMapper mapper;

//    public void registerDefaultUser(User user) {
//        Role roleUser = roleRepo.findByName("User");
//        user.addRole(roleUser);
//        user.setActive(true);
//        String encodedPass = encoder.encode(user.getPassword());
//        user.setPassword(encodedPass);
//
//        userRepo.save(user);
//    }


    @Override
    @Transactional
    public User save(UserDTO userDTO) {
        userDTO.setActive(true);
        User user = new User(userDTO.getEmail(),userDTO.getUserName(), userDTO.getPassword(), userDTO.getFirstName(), userDTO.getLastName(),userDTO.getActive(), Arrays.asList(new Role("ROLE_USER")));
        return userRepo.save(user);
    }

    public void setUserRepo(UserRepository userRepo) {
        this.userRepo = userRepo;
    }
}

