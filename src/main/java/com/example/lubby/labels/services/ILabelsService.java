package com.example.lubby.labels.services;

import com.example.lubby.labels.LabelEntity;
import com.example.lubby.labels.dto.CreateLabelDTO;
import org.springframework.http.ResponseEntity;

public interface ILabelsService {
    public ResponseEntity<LabelEntity> createLabel(CreateLabelDTO dto, String emailUser);
}
