package com.inmar.poc.controller;

import com.inmar.poc.dto.AuthResponseDto;
import com.inmar.poc.dto.LoginDto;
import com.inmar.poc.dto.RegisterUserDto;
import com.inmar.poc.entity.User;
import com.inmar.poc.service.AuthenticationService;
import com.inmar.poc.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final JwtUtil jwtUtil;

    private final AuthenticationService authenticationService;

    public AuthController(JwtUtil jwtUtil, AuthenticationService authenticationService) {
        this.jwtUtil = jwtUtil;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> authenticate(@RequestBody LoginDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtUtil.generateToken(authenticatedUser);

        AuthResponseDto authResponse = new AuthResponseDto();
        authResponse.setAccessToken(jwtToken);
        authResponse.setExpiresIn(jwtUtil.getExpirationTime());

        return ResponseEntity.ok(authResponse);
    }

}

