package com.project.dci.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class User {
    private String profileNickname;
    private String profileThumnailImage;
    private boolean allowed_msg;
    private Long id;
    private String uuid;
    private boolean favorite;
}
