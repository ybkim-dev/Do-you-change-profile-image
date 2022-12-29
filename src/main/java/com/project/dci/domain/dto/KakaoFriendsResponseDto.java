package com.project.dci.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class KakaoFriendsResponseDto {
    private UserResponseDto[] elements;
    @JsonProperty("total_cnt")
    private int totalCnt;
    @JsonProperty("before_url")
    private String beforeUrl;
    @JsonProperty("after_url")
    private String afterUrl;
    @JsonProperty("favorite_count")
    private int favoriteCount;
}
