package com.FinalProjectV2.FinalProject;


import com.FinalProjectV2.FinalProject.Repository.RoleRepository;
import com.FinalProjectV2.FinalProject.Repository.UserRepository;
import com.FinalProjectV2.FinalProject.entity.Role;
import com.FinalProjectV2.FinalProject.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepoTests {


    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setEmail("test@test.com");
        user.setPassword("bbbb");
        user.setFirstName("Bogdan");
        user.setLastName("Radu");
        user.setUserName("bogdibbb");
        user.setActive(true);

        User savedUser = userRepo.save(user);

        User existUser = entityManager.find(User.class, savedUser.getId());

        assertThat(user.getEmail()).isEqualTo(existUser.getEmail());

    }

    @Test
    public void testAddRoleToNewUser() {
        Role roleUser = roleRepo.findByName("User");

        User user = new User();
        user.setEmail("test@gmail.com");
        user.setPassword("mike2020");
        user.setFirstName("Mike");
        user.setLastName("Gates");
        user.addRole(roleUser);

        User savedUser = userRepo.save(user);

        assertThat(savedUser.getRoles().size()).isEqualTo(1);
    }

    @Test
    public void testAddRoleToExistingUser() {
        User user = userRepo.findById(1L).get();
        Role roleAdmin = roleRepo.findByName("Admin");

        user.addRole(roleAdmin);


        User savedUser = userRepo.save(user);

        assertThat(savedUser.getRoles().size()).isEqualTo(2);
    }
}

