package com.project.dci.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class KakaoMessageTemplateRequestDto {
    private final String objectType = "text";
    private String text;
    private KakaoMessageLinkRequestDto link;
    private final String buttonTitle = "카카오 디벨로퍼 홈페이지";

    @Override
    public String toString() {
        return "{ " +
                "\"object_type\" : " + "\"" + objectType + "\"" + "," +
                "\"text\" : " + "\""  + text + "\"" +  "," +
                "\"link\" : { " + link.toString() + "}," +
                "\"button_title\" : " + "\"" + buttonTitle + "\"" + "}";
    }
}
