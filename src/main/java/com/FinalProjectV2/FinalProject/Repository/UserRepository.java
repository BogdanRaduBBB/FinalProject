package com.FinalProjectV2.FinalProject.Repository;

import com.FinalProjectV2.FinalProject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
}
