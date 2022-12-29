package com.project.dci.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Builder
public class KakaoMessageLinkRequestDto {
    @JsonProperty("web_url")
    private final String webUrl = "https://developers.kakao.com";

    @Override
    public String toString() {
        return "\"web_url\" : " + "\"" + webUrl + "\"";
    }
}
