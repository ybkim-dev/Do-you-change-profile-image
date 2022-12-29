package com.project.dci.domain.repository;

import com.project.dci.domain.dto.KakaoFriendsResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;

@Repository
public class KakaoApiRepository {
    @Value("${config.api.get-friends.url}")
    private String friendsUrl;
    @Value("${config.api.get-friends.key}")
    private String friendsToken;

    public KakaoFriendsResponseDto findFriends() {
        URI uri = UriComponentsBuilder
                .fromUriString(friendsUrl)
                .queryParam("limit", 100)
                .build()
                .encode(StandardCharsets.UTF_8)
                .toUri();

        HttpEntity<Object> httpEntity = setHttpEntity();
        ParameterizedTypeReference<KakaoFriendsResponseDto> responseType = new ParameterizedTypeReference<>(){};
        ResponseEntity<KakaoFriendsResponseDto> response = requestApi(uri, httpEntity, responseType);
        return response.getBody();
    }

    private ResponseEntity<KakaoFriendsResponseDto> requestApi(URI uri, HttpEntity<Object> httpEntity, ParameterizedTypeReference<KakaoFriendsResponseDto> responseType) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<KakaoFriendsResponseDto> response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                responseType
        );
        return response;
    }

    private HttpEntity<Object> setHttpEntity() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer " + friendsToken);
        HttpEntity<Object> httpEntity = new HttpEntity<>(httpHeaders);
        return httpEntity;
    }

}
