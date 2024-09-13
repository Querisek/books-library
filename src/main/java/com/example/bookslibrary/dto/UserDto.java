package com.example.bookslibrary.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserDto {

    @NotEmpty(message = "Enter the username.")
    @Size(min = 4, max = 30, message = "Username should be at least 4 characters long.")
    private String username;

    @NotEmpty(message = "Enter the email.")
    @Size(min = 5, max = 30, message = "Email should be at least 5 characters long.")
    @Email(message = "Enter a valid email format.")
    private String email;

    @NotEmpty(message = "Enter the password.")
    @Size(min = 5, max = 60, message = "Password should be at least 5 characters long.")
    private String password;

    public UserDto() {

    }

    public UserDto(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public @NotEmpty(message = "Enter the username.") @Size(min = 4, max = 30, message = "Username should be at least 4 characters long.") String getUsername() {
        return username;
    }

    public void setUsername(@NotEmpty(message = "Enter the username.") @Size(min = 4, max = 30, message = "Username should be at least 4 characters long.") String username) {
        this.username = username;
    }

    public @NotEmpty(message = "Enter the email.") @Size(min = 5, max = 30, message = "Email should be at least 5 characters long.") @Email(message = "Enter a valid email format.") String getEmail() {
        return email;
    }

    public void setEmail(@NotEmpty(message = "Enter the email.") @Size(min = 5, max = 30, message = "Email should be at least 5 characters long.") @Email(message = "Enter a valid email format.") String email) {
        this.email = email;
    }

    public @NotEmpty(message = "Enter the password.") @Size(min = 5, max = 60, message = "Password should be at least 5 characters long.") String getPassword() {
        return password;
    }

    public void setPassword(@NotEmpty(message = "Enter the password.") @Size(min = 5, max = 60, message = "Password should be at least 5 characters long.") String password) {
        this.password = password;
    }
}
