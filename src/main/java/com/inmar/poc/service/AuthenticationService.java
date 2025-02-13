package com.inmar.poc.service;

import com.inmar.poc.dto.LoginDto;
import com.inmar.poc.dto.RegisterUserDto;
import com.inmar.poc.entity.User;
import com.inmar.poc.enums.Role;
import com.inmar.poc.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(RegisterUserDto input) {
        User user = new User();
        user.setName(input.getName());

        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        if ("admin@gmail.com".equals(input.getEmail())) {
            user.setRoles(List.of(Role.ADMIN));
        } else {
            user.setRoles(List.of(Role.USER));
        }

        return userRepository.save(user);
    }

    public User authenticate(LoginDto input) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        User user = userRepository.findByEmail(input.getEmail())
                .orElseThrow();

        return user;
    }
}
