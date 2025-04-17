package com.example.EduConnect.service;

import com.example.EduConnect.dto.AuthenticationRequest;
import com.example.EduConnect.dto.AuthenticationResponse;
import com.example.EduConnect.dto.RegisterRequest;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
