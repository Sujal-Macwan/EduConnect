package com.example.EduConnect.impl;

import com.example.EduConnect.dto.AuthenticationRequest;
import com.example.EduConnect.dto.AuthenticationResponse;
import com.example.EduConnect.dto.RegisterRequest;
import com.example.EduConnect.entity.User;
import com.example.EduConnect.repository.UserRepository;
import com.example.EduConnect.service.AuthenticationService;
import com.example.EduConnect.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("User with this email already exists");
        }
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        userRepository.save(user);

        String token = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    @Transactional
    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request){
        System.out.println("Inside authenticate(): " + this.getClass().getName());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtService.generateToken(user);

        return new AuthenticationResponse(token);
//        System.out.println("Inside authenticate()");
//
//        // First step: Test the authenticationManager.authenticate()
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        request.getEmail(),
//                        request.getPassword()
//                )
//        );
//
//        return new AuthenticationResponse("TEST - Step 1");
    }
}
