package com.example.lubby.labels.dto;

import com.example.lubby.labels.enums.TypeLabelsEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateLabelDTO {
    private LocalDateTime createdAt;

    private String name;

    private String icon;

    private Long color;

    private TypeLabelsEnum type;
}
