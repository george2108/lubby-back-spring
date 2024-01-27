package com.example.lubby.labels.services;

import com.example.lubby.common.dto.HttpException;
import com.example.lubby.labels.LabelEntity;
import com.example.lubby.labels.dto.CreateLabelDTO;
import com.example.lubby.labels.repositories.LabelRepository;
import com.example.lubby.users.UserEntity;
import com.example.lubby.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class LabelsService implements ILabelsService{
    @Autowired
    private LabelRepository labelRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<LabelEntity> createLabel(CreateLabelDTO dto, String emailUser) {
        Optional<UserEntity> user = userRepository.findByEmail(emailUser);

        if(user.isEmpty()){
            HttpException httpException = HttpException.builder()
                    .message("Usuario no encotnrado")
                    .statusCode(HttpStatus.NOT_FOUND.value())
                    .status(HttpStatus.NOT_FOUND)
                    .build();
            return new ResponseEntity(httpException, HttpStatus.NOT_FOUND);
        }

        LabelEntity labelToCreate = LabelEntity.builder()
                .createdAt(dto.getCreatedAt())
                .color(dto.getColor())
                .icon(dto.getIcon())
                .type(dto.getType())
                .name(dto.getName())
                .user(user.get())
                .build();

        LabelEntity labelCreated = labelRepository.save(labelToCreate);

        return new ResponseEntity<LabelEntity>(labelCreated, HttpStatus.CREATED);
    }
}
