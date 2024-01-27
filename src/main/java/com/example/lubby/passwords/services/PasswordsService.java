package com.example.lubby.passwords.services;

import com.example.lubby.common.dto.HttpException;
import com.example.lubby.labels.LabelEntity;
import com.example.lubby.labels.repositories.LabelRepository;
import com.example.lubby.passwords.PasswordEntity;
import com.example.lubby.passwords.dto.CreatePasswordDTO;
import com.example.lubby.passwords.repositories.PasswordRepository;
import com.example.lubby.users.UserEntity;
import com.example.lubby.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PasswordsService implements IPasswordsService{
    @Autowired
    private PasswordRepository passwordRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LabelRepository labelRepository;

    @Override
    public ResponseEntity<PasswordEntity> createPassword(CreatePasswordDTO passwordDTO, String userEmail) {
        Optional<UserEntity> user = userRepository.findByEmail(userEmail);

        if(user.isEmpty()){
            HttpException exception = HttpException.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .statusCode(HttpStatus.NOT_FOUND.value())
                    .message("Usuario no encontrado")
                    .build();
            return new ResponseEntity(exception, HttpStatus.NOT_FOUND);
        }

        Optional<LabelEntity> labelEntity = Optional.empty();
        if(passwordDTO.getLabelId() != null){
            labelEntity = labelRepository.findById(passwordDTO.getLabelId());
        }

        PasswordEntity passwordEntity = PasswordEntity.builder()
                .createdAt(passwordDTO.getCreatedAt())
                .password(passwordDTO.getPassword())
                .color(passwordDTO.getColor())
                .description(passwordDTO.getDescription())
                .url(passwordDTO.getUrl())
                .favorite(passwordDTO.getFavorite())
                .icon(passwordDTO.getIcon())
                .notas(passwordDTO.getNotas())
                .title(passwordDTO.getTitle())
                .username(passwordDTO.getUserName())
                .user(user.get())
                .label(labelEntity.orElse(null))
                .build();
        PasswordEntity passwordCreated = passwordRepository.save(passwordEntity);

        return new ResponseEntity<PasswordEntity>(passwordCreated, HttpStatus.CREATED);
    }
}
