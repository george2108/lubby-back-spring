package com.example.lubby.auth;

import com.example.lubby.auth.dto.LoginRequestDTO;
import com.example.lubby.auth.dto.LoginResponseDTO;
import com.example.lubby.auth.dto.RegisterResponseDTO;
import com.example.lubby.auth.services.IAuthService;
import com.example.lubby.users.dto.CreateUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private IAuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @RequestBody LoginRequestDTO data
    ) {
        return authService.login(data);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(
            @RequestBody CreateUserDTO userDTO
    ) {
        return authService.register(userDTO);
    }
}
