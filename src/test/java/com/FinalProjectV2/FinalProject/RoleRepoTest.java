package com.FinalProjectV2.FinalProject;


import com.FinalProjectV2.FinalProject.Repository.RoleRepository;
import com.FinalProjectV2.FinalProject.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class RoleRepoTest {
    @Autowired
    private RoleRepository repo;

    @Test
    public void testCreateRoles() {
        Role user = new Role("User");
        Role admin = new Role("Admin");

        repo.saveAll(List.of(user, admin));

        List<Role> listRoles = repo.findAll();

        assertThat(listRoles.size()).isEqualTo(2);
    }
}
