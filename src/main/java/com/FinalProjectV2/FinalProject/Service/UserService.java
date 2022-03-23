package com.FinalProjectV2.FinalProject.Service;


import com.FinalProjectV2.FinalProject.Repository.RoleRepository;
import com.FinalProjectV2.FinalProject.Repository.UserRepository;
import com.FinalProjectV2.FinalProject.dto.UserDTO;
import com.FinalProjectV2.FinalProject.entity.Role;
import com.FinalProjectV2.FinalProject.entity.User;
//import com.FinalProjectV2.FinalProject.mapper.UserMapper;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Service
@SessionAttributes(value = "user")
public class UserService implements UserServiceInterface {

    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public UserService(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }


    @Autowired
    private UserRepository userRepo;

    @Override
    public User save(UserDTO userDTO) {
        userDTO.setActive(true);
        encoder.encode(userDTO.getPassword());
        User user = new User(userDTO.getEmail(),userDTO.getUserName(), encoder.encode(userDTO.getPassword()), userDTO.getFirstName(), userDTO.getLastName(),userDTO.getActive(), Arrays.asList(new Role("ROLE_USER")));
        return userRepo.save(user);
    }

    public User findById(Long id) throws Exception {
        User user = userRepo.findById(id).orElse(null);
        if (user==null) {
            throw new Exception("Cannot find Contact with id: " + id);
        }
        else return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}

