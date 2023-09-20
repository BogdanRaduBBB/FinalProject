package com.FinalProject.FinalProject.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@ToString
@Entity
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 45)
    private String email;
    @Column(name = "user_name")
    private String userName;
    @Column(nullable = false, length = 64)
    private String password;
    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;
    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;
    @Column(name = "active")
    private Boolean active;
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    @ToString.Exclude
    @Column(name = "role")
    private Role userRole;

    public User(String email, String userName, String password, String firstName, String lastName) {
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(String email, String userName, String password, String firstName, String lastName, Boolean active, Role role) {
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.active = active;
        this.userRole = role;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.getName());
        return Collections.singletonList(authority);
    }

    public String getUsername() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public boolean isEnabled() {
        return active;
    }

    public void setUserRole(Role role) {
        this.userRole = role;
    }

    public Role getUserRole(Role role) {
        return role;
    }

}
