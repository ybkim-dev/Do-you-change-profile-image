package com.project.dci.web.controller.api;

import com.project.dci.domain.dto.*;
import com.project.dci.domain.service.MessageService;
import com.project.dci.domain.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@Slf4j
public class MessageController {

    private final UserService userService;
    private final MessageService messageService;

    @GetMapping("/api/message/result")
    public ResponseEntity<KakaoMessageResponseDto> getUsers(@RequestParam String query) {
        List<UserResponseDto> changedFavoriteFriends = userService.getProfileImageChangedFavoriteFriends();
        // 최대 5명에게만 보내기 위해 5명 추림
        List<UserResponseDto> atMostFiveUsers = messageService.getAtMostFiveUsers(changedFavoriteFriends);

        if(atMostFiveUsers.size() == 0) {
            log.info("메시지 보낸 유저 없음");
            return ResponseEntity.ok().build();
        }
        KakaoMessageRequestDto messageRequestDto = KakaoMessageRequestDto.builder()
                .templateObject(KakaoMessageTemplateRequestDto.builder()
                        .link(KakaoMessageLinkRequestDto.builder().build())
                        .text(query)
                        .build()
                )
                .receiverUuids(atMostFiveUsers.stream().map(UserResponseDto::getUuid).collect(Collectors.toList()))
                .build();
        // 메시지 전송하기
        KakaoMessageResponseDto messageResponseDto = messageService.sendMessage(messageRequestDto);
        // 메시지 전송한 유저의 프로필 이미지 변경
        userService.updateProfileImage(atMostFiveUsers);
        log.info(MessageFormat.format("메시지 전송된 유저 목록들 : {0}", messageRequestDto.getReceiverUuids()));
        log.info(MessageFormat.format("전송한 메시지 : {0}", query));
        return ResponseEntity.ok(messageResponseDto);
    }
}
