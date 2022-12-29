package com.project.dci.domain.service;

import com.project.dci.domain.dto.KakaoMessageRequestDto;
import com.project.dci.domain.dto.KakaoMessageResponseDto;
import com.project.dci.domain.dto.UserResponseDto;
import com.project.dci.domain.repository.KakaoMessageApiRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MessageService {

    private final KakaoMessageApiRepository kakaoMessageApiRepository;

    public List<UserResponseDto> getAtMostFiveUsers(List<UserResponseDto> candidates) {
        if(candidates.size() <= 5) return candidates;
        else {
            List<UserResponseDto> users = new ArrayList<>();
            for(int i = 0; i < 5; i++) {
                users.add(candidates.get(i));
            }
            return users;
        }
    }

    public KakaoMessageResponseDto sendMessage(KakaoMessageRequestDto messageRequestDto) {
        KakaoMessageResponseDto kakaoMessageResponseDto = kakaoMessageApiRepository.sendMessage(messageRequestDto);
        if(kakaoMessageResponseDto.getSuccessfulReceiverUuids() == null) {
            kakaoMessageResponseDto.initSuccessfulReceiverUuids();
        }
        return kakaoMessageResponseDto;
    }
}
