package com.example.bookslibrary.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class UserDto {

    @NotEmpty(message = "Enter the username.")
    private String username;

    @NotEmpty(message = "Enter the email.")
    @Email(message = "Enter a valid email format.")
    private String email;

    @NotEmpty(message = "Enter the password.")
    private String password;

    public UserDto() {

    }

    public UserDto(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
