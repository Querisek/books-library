package com.example.bookslibrary.dto;

import jakarta.validation.constraints.*;

public class UserDto {

    private Long id;

    @NotBlank(message = "Enter the username.")
    @Size(min = 4, max = 30, message = "Username should be at least 4 characters long.")
    private String username;

    @NotBlank(message = "Enter the email.")
    @Size(min = 5, max = 30, message = "Email should be at least 5 characters long.")
    @Email(message = "Enter a valid email format.")
    private String email;

    @NotBlank(message = "Enter the password.")
    @Size(min = 5, max = 60, message = "Password should be at least 5 characters long.")
    private String password;

    public UserDto() {

    }

    public UserDto(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Enter the username.") @Size(min = 4, max = 30, message = "Username should be at least 4 characters long.") String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank(message = "Enter the username.") @Size(min = 4, max = 30, message = "Username should be at least 4 characters long.") String username) {
        this.username = username;
    }

    public @NotBlank(message = "Enter the email.") @Size(min = 5, max = 30, message = "Email should be at least 5 characters long.") @Email(message = "Enter a valid email format.") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Enter the email.") @Size(min = 5, max = 30, message = "Email should be at least 5 characters long.") @Email(message = "Enter a valid email format.") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Enter the password.") @Size(min = 5, max = 60, message = "Password should be at least 5 characters long.") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Enter the password.") @Size(min = 5, max = 60, message = "Password should be at least 5 characters long.") String password) {
        this.password = password;
    }
}
