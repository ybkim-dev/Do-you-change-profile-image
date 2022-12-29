package com.project.dci.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserResponseDto {
    private String profileNickname;
    private String profileThumnailImage;
    private boolean allowed_msg;
    private Long id;
    private String uuid;
    private boolean favorite;
}
