package com.example.bookslibrary.controller;

import com.example.bookslibrary.dto.UserDto;
import com.example.bookslibrary.model.User;
import com.example.bookslibrary.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "registration";
    }

    @PostMapping("/register")
    public String saveUser(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result) {
        if(userService.existsByUsername(userDto.getUsername())) {
            result.rejectValue("username", "error.username", "Account with this username already exists.");
        }
        if(userService.existsByEmail(userDto.getEmail())) {
            result.rejectValue("email", "error.email", "Account with this email already exists.");
        }
        if(result.hasErrors()) {
            return "registration";
        }
        userService.saveUser(userDto);
        return "redirect:/register?success";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
