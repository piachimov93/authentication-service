package com.piachimov.authenticationservice.controller;

import com.piachimov.authenticationservice.dto.UserRequestDto;
import com.piachimov.authenticationservice.service.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public String addUser(@RequestBody UserRequestDto userRequestDto) {
        return authenticationService.saveUser(userRequestDto);
    }

    @GetMapping("/token")
    public String generateToken(@RequestBody UserRequestDto userRequestDto) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequestDto.username(), userRequestDto.password()));

        if (authenticate.isAuthenticated()) {
            return authenticationService.generateToken(userRequestDto.username());
        } else {
            throw new BadCredentialsException("Bad credentials");
        }
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        authenticationService.validateToken(token);
        return "Token is valid";
    }
}
