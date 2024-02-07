package com.example.lubby.passwords;

import com.example.lubby.common.dto.DeleteResultDTO;
import com.example.lubby.passwords.dto.CreatePasswordDTO;
import com.example.lubby.passwords.dto.UpdatePasswordDTO;
import com.example.lubby.passwords.services.IPasswordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("{id}")
    public ResponseEntity<PasswordEntity> updatePassword(
            @RequestBody UpdatePasswordDTO passwordDTO,
            Authentication authentication,
            @PathVariable("id") Long id
    ){
        return passwordsService.updatePassword(id, passwordDTO, authentication.getName());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DeleteResultDTO> deletePassword(
            @PathVariable("id") Long id,
            Authentication authentication
    ){
        return passwordsService.deletePassword(id, authentication.getName());
    }
}
