package com.FinalProjectV2.FinalProject.Repository;

import com.FinalProjectV2.FinalProject.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String role);
}
