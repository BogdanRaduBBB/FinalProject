package com.FinalProjectV2.FinalProject.Service;


import com.FinalProjectV2.FinalProject.Repository.RoleRepository;
import com.FinalProjectV2.FinalProject.Repository.UserRepository;
import com.FinalProjectV2.FinalProject.entity.Role;
import com.FinalProjectV2.FinalProject.entity.User;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired RoleRepository roleRepo;


    public void registerDefaultUser(User user) {
        Role roleUser = roleRepo.findByName("User");
        user.addRole(roleUser);

        userRepo.save(user);
    }

}

