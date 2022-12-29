package com.project.dci.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@ToString
public class KakaoMessageResponseDto {
    @JsonProperty("successful_receiver_uuids")
    private List<String> successfulReceiverUuids;

    public void initSuccessfulReceiverUuids() {
        successfulReceiverUuids = new ArrayList<>();
    }
}
