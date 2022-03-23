package com.FinalProjectV2.FinalProject.Service;

import com.FinalProjectV2.FinalProject.entity.User;
import com.FinalProjectV2.FinalProject.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserServiceInterface extends UserDetailsService {
    User save(UserDTO userDTO);
}
