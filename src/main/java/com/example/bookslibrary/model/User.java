package com.example.bookslibrary.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @NotEmpty
    @Size(min = 4, max = 30)
    @Column(name = "username")
    private String username;

    @NotEmpty
    @Size(min = 5, max = 30)
    @Email
    @Column(name = "email")
    private String email;

    @NotEmpty
    @Size(min = 5, max = 60)
    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    public User() {

    }

    public User(String username, String email, String password, List<Role> roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public @NotEmpty @Size(min = 4, max = 30) String getUsername() {
        return username;
    }

    public void setUsername(@NotEmpty @Size(min = 4, max = 30) String username) {
        this.username = username;
    }

    public @NotEmpty @Size(min = 5, max = 100) @Email String getEmail() {
        return email;
    }

    public void setEmail(@NotEmpty @Size(min = 5, max = 100) @Email String email) {
        this.email = email;
    }

    public @NotEmpty @Size(min = 5, max = 60) String getPassword() {
        return password;
    }

    public void setPassword(@NotEmpty @Size(min = 5, max = 60) String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
