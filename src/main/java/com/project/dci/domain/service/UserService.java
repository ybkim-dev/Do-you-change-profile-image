package com.project.dci.domain.service;

import com.project.dci.domain.User;
import com.project.dci.domain.dto.KakaoFriendsResponseDto;
import com.project.dci.domain.dto.UserResponseDto;
import com.project.dci.domain.repository.KakaoUserApiRepository;
import com.project.dci.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@Service
public class UserService {

    private final KakaoUserApiRepository kakaoUserApiRepository;
    private final UserRepository memoryUserRepository;

    public void updateProfileImage(List<UserResponseDto> users) {
        users.forEach((user) -> {
            memoryUserRepository.upsertUser(user.toUser());
        });
    }

    public List<UserResponseDto> getProfileImageChangedFavoriteFriends() {
        KakaoFriendsResponseDto friendsInfo = kakaoUserApiRepository.findFriendsInfo();

        // 즐겨찾기 친구 필터링 && 프로필 사진 변경된 친구 필터링
         return Stream.of(friendsInfo.getElements())
                .filter(UserResponseDto::isFavorite)
                .peek((user) -> {
                    if (memoryUserRepository.findUserByUUID(user.getUuid()).isEmpty())
                        memoryUserRepository.saveUser(user.toUser());
                })
                .filter((user) -> {
                    User foundUser = memoryUserRepository.findUserByUUID(user.getUuid())
                            .orElseThrow(() -> new IllegalArgumentException(MessageFormat.format("해당 유저가 없습니다. User ID = {0}", user.getUuid())));
                    return !user.getProfileThumbnailImage().equals(foundUser.getProfileThumbnailImage());
                })
                .collect(Collectors.toList());
    }
}
