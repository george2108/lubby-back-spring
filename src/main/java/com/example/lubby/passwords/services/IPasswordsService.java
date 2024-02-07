package com.example.lubby.passwords.services;

import com.example.lubby.common.dto.DeleteResultDTO;
import com.example.lubby.passwords.PasswordEntity;
import com.example.lubby.passwords.dto.CreatePasswordDTO;
import com.example.lubby.passwords.dto.UpdatePasswordDTO;
import org.springframework.http.ResponseEntity;

public interface IPasswordsService {
    public ResponseEntity<PasswordEntity> createPassword(CreatePasswordDTO passwordDTO, String userEmail);

    public ResponseEntity<PasswordEntity> updatePassword(Long id, UpdatePasswordDTO passwordDTO, String userEmail);

    public ResponseEntity<DeleteResultDTO> deletePassword(Long id, String userEmail);
}
