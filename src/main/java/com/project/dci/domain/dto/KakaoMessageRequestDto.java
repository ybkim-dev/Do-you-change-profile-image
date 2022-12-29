package com.project.dci.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class KakaoMessageRequestDto {
    private KakaoMessageTemplateRequestDto templateObject;
    private List<String> receiverUuids;

    public MultiValueMap<String, String> toMultiValueMap() {
        StringBuilder sb = new StringBuilder();
        LinkedMultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("template_object", templateObject.toString());
        sb.append("[");
        for(String uuid : receiverUuids) {
            sb.append("\"").append(uuid).append("\"").append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        map.add("receiver_uuids", sb.toString());
        return map;
    }
}
