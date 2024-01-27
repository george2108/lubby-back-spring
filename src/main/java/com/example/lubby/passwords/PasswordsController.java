package com.example.lubby.passwords;

import com.example.lubby.passwords.dto.CreatePasswordDTO;
import com.example.lubby.passwords.services.IPasswordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/passwords")
public class PasswordsController {
    @Autowired
    private IPasswordsService passwordsService;

    @PostMapping
    public ResponseEntity<PasswordEntity> createPassword(
            Authentication authentication,
            @RequestBody CreatePasswordDTO passwordDTO
    ) {
        return passwordsService.createPassword(passwordDTO, authentication.getName());
    }
}
