package com.project.dci.domain.repository;

import com.project.dci.domain.dto.KakaoMessageRequestDto;
import com.project.dci.domain.dto.KakaoMessageResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;

@Component
public class KakaoMessageApiRepository {

    @Value("${config.api.send-message.url}")
    private String messageUrl;
    @Value("${config.api.key}")
    private String messageToken;

    public KakaoMessageResponseDto sendMessage(KakaoMessageRequestDto messageRequestDto) {
        URI uri = UriComponentsBuilder
                .fromUriString(messageUrl)
                .build()
                .encode(StandardCharsets.UTF_8)
                .toUri();
        HttpEntity<Object> httpEntity = setHttpEntity(messageRequestDto);
        ParameterizedTypeReference<KakaoMessageResponseDto> responseType = new ParameterizedTypeReference<>() {};
        ResponseEntity<KakaoMessageResponseDto> response = requestApi(uri, httpEntity, responseType);
        return response.getBody();
    }

    private ResponseEntity<KakaoMessageResponseDto> requestApi(URI uri, HttpEntity<Object> httpEntity, ParameterizedTypeReference<KakaoMessageResponseDto> responseType) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<KakaoMessageResponseDto> response = restTemplate.exchange(
                uri,
                HttpMethod.POST,
                httpEntity,
                responseType
        );
        return response;
    }

    private HttpEntity<Object> setHttpEntity(KakaoMessageRequestDto messageRequestDto) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer " + messageToken);
        httpHeaders.set("Content-Type", "application/x-www-form-urlencoded");
        MultiValueMap<String, String> stringStringMultiValueMap = messageRequestDto.toMultiValueMap();
        HttpEntity<Object> httpEntity = new HttpEntity<>(stringStringMultiValueMap, httpHeaders);
        return httpEntity;
    }
}
