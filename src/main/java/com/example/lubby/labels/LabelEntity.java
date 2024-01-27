package com.example.lubby.labels;

import com.example.lubby.labels.enums.TypeLabelsEnum;
import com.example.lubby.users.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "labels")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LabelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private String name;

    private String icon;

    private Long color;

    @Enumerated(EnumType.STRING)
    private TypeLabelsEnum type;

    @ManyToOne(targetEntity = UserEntity.class)
    private UserEntity user;
}
