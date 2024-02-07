package com.example.lubby.passwords.services;

import com.example.lubby.common.dto.DeleteResultDTO;
import com.example.lubby.common.dto.HttpException;
import com.example.lubby.labels.LabelEntity;
import com.example.lubby.labels.repositories.LabelRepository;
import com.example.lubby.passwords.PasswordEntity;
import com.example.lubby.passwords.dto.CreatePasswordDTO;
import com.example.lubby.passwords.dto.UpdatePasswordDTO;
import com.example.lubby.passwords.repositories.PasswordRepository;
import com.example.lubby.users.UserEntity;
import com.example.lubby.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
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

    @Override
    public ResponseEntity<PasswordEntity> updatePassword(Long id, UpdatePasswordDTO passwordDTO, String userEmail) {
        Optional<UserEntity> user = userRepository.findByEmail(userEmail);

        if(user.isEmpty()){
            HttpException exception = HttpException.builder()
                    .message("Usuario no encontrado")
                    .build();
            return new ResponseEntity(exception, HttpStatus.NOT_FOUND);
        }

        Optional<LabelEntity> labelEntity = Optional.empty();
        if(passwordDTO.getLabelId() != null){
            labelEntity = labelRepository.findById(passwordDTO.getLabelId());
        }

        PasswordEntity passwordEntity = PasswordEntity.builder()
                .id(id)
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
        PasswordEntity passwordUpdated = passwordRepository.save(passwordEntity);

        return new ResponseEntity<PasswordEntity>(passwordUpdated, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<DeleteResultDTO> deletePassword(Long id, String userEmail) {
        try {
            Optional<UserEntity> user = userRepository.findByEmail(userEmail);

            if(user.isEmpty()){
                return new ResponseEntity(
                        HttpException.builder().message("Usuario no encontrado").build(),
                        HttpStatus.NOT_FOUND
                );
            }

            Optional<PasswordEntity> password = passwordRepository.findById(id);
            if(password.isEmpty()){
                return new ResponseEntity(
                        HttpException.builder().message("Usuario no encontrado").build(),
                        HttpStatus.NOT_FOUND
                );
            }

            if (!Objects.equals(password.get().getUser().getId(), user.get().getId())){
                return new ResponseEntity(
                        HttpException.builder().message("La contraseña no le pertenece al usuario").build(),
                        HttpStatus.NOT_FOUND
                );
            }

            passwordRepository.deleteById(id);
            return new ResponseEntity<DeleteResultDTO>(DeleteResultDTO.builder().deleted(true).build(), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(
                    HttpException.builder().message("No se pudo eliminar la información").build(),
                    HttpStatus.CONFLICT
            );
        }


    }
}
