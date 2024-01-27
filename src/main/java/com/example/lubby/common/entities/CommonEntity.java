package com.example.lubby.common.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.LastModifiedDate;
import java.time.LocalDate;

public abstract class CommonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    private LocalDate createdAt;

    @LastModifiedDate
    private LocalDate updatedAt;

    @Column(columnDefinition = "boolean default true", nullable = false)
    private boolean active;
}
