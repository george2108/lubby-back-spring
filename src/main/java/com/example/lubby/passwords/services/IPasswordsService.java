package com.example.lubby.passwords.services;

import com.example.lubby.passwords.PasswordEntity;
import com.example.lubby.passwords.dto.CreatePasswordDTO;
import org.springframework.http.ResponseEntity;

public interface IPasswordsService {
    public ResponseEntity<PasswordEntity> createPassword(CreatePasswordDTO passwordDTO, String userEmail);
}
