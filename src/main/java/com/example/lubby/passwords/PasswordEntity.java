package com.example.lubby.passwords;

import com.example.lubby.labels.LabelEntity;
import com.example.lubby.users.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "passwords")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PasswordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private String title;

    private String username;

    private String password;

    private String description;

    private boolean favorite;

    private String url;

    private String notas;

    private Long color;

    private String icon;

    @ManyToOne(targetEntity = LabelEntity.class, fetch = FetchType.LAZY)
    private LabelEntity label;

    @ManyToOne(targetEntity = UserEntity.class, fetch = FetchType.LAZY)
    private UserEntity user;
}
