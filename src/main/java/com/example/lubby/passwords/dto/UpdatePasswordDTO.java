package com.example.lubby.passwords.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdatePasswordDTO {
    private Boolean active;

    private String title;

    private String userName;

    private String password;

    private String description;

    private Boolean favorite;

    private String url;

    private String notas;

    private Long color;

    private String icon;

    private Long labelId;
}
