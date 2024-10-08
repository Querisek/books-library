package com.example.bookslibrary.service;

import com.example.bookslibrary.dto.UserDto;
import com.example.bookslibrary.model.Role;
import com.example.bookslibrary.model.User;
import com.example.bookslibrary.repository.RoleRepository;
import com.example.bookslibrary.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    public UserDto findUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if(user != null) {
            return modelMapper.map(user, UserDto.class);
        } else {
            throw new UsernameNotFoundException("Account with this username doesn't exist.");
        }
    }

    public UserDto findUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if(user != null) {
            return modelMapper.map(user, UserDto.class);
        } else {
            throw new UsernameNotFoundException("Account with this email doesn't exist.");
        }
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Transactional
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role = roleRepository.findByName("ROLE_USER");
        if(role != null) {
            user.setRoles(List.of(role));
        } else {
            role = new Role("ROLE_USER");
            roleRepository.save(role);
            user.setRoles(List.of(role));
        }
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user != null) {
            return new org.springframework.security.core.userdetails.User(user.getEmail(),
                    user.getPassword(),
                    mapRolesToAuthorities(user.getRoles()));
            } else {
                throw new UsernameNotFoundException("Wrong username or password.");
            }
        }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
