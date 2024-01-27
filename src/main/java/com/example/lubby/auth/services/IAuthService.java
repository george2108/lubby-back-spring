package com.example.lubby.auth.services;

import com.example.lubby.auth.dto.LoginRequestDTO;
import com.example.lubby.auth.dto.LoginResponseDTO;
import com.example.lubby.auth.dto.RegisterResponseDTO;
import com.example.lubby.users.dto.CreateUserDTO;
import com.example.lubby.users.UserEntity;
import org.springframework.http.ResponseEntity;

public interface IAuthService {
    public ResponseEntity<LoginResponseDTO> login(LoginRequestDTO data);

    public ResponseEntity<RegisterResponseDTO> register(CreateUserDTO user);
}
