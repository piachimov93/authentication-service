package com.piachimov.authenticationservice.service.auth;

import com.piachimov.authenticationservice.dto.UserRequestDto;
import com.piachimov.authenticationservice.entity.UserEntity;
import com.piachimov.authenticationservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }


    public String saveUser(UserRequestDto userRequestDto) {
        var user = new UserEntity();

        user.setUsername(userRequestDto.username());
        user.setPassword(passwordEncoder.encode(userRequestDto.password()));

        userRepository.save(user);
        return "User saved successfully!";
    }
}
