package com.example.lubby.labels;

import com.example.lubby.labels.dto.CreateLabelDTO;
import com.example.lubby.labels.services.ILabelsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/labels")
public class LabelController {
    @Autowired
    private ILabelsService labelsService;

    @PostMapping
    public ResponseEntity<LabelEntity> createLabel(
            Authentication authentication,
            @RequestBody CreateLabelDTO dto
    ){
        return labelsService.createLabel(dto, authentication.getName());
    }
}
