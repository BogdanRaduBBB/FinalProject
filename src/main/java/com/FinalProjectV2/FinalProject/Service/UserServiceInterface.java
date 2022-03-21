package com.FinalProjectV2.FinalProject.Service;

import com.FinalProjectV2.FinalProject.entity.User;
import com.FinalProjectV2.FinalProject.dto.UserDTO;

public interface UserServiceInterface {
    User save(UserDTO userDTO);
}
