package com.project.dci.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class User {
    private String profileNickname;
    private String profileThumbnailImage;
    private boolean allowed_msg;
    private Long id;
    private String uuid;
    private boolean favorite;
}
