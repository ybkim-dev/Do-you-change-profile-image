package com.project.dci.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.dci.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserResponseDto {
    @JsonProperty("profile_nickname")
    private String profileNickname;
    @JsonProperty("profile_thumbnail_image")
    private String profileThumbnailImage;
    @JsonProperty("allowed_msg")
    private boolean allowedMsg;
    private Long id;
    private String uuid;
    private boolean favorite;

    public User toUser() {
        return new User(profileNickname, profileThumbnailImage, allowedMsg, id, uuid, favorite);
    }
}
