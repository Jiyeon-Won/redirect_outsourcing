package com.sparta.redirect_outsourcing.domain.user.repository;

import com.sparta.redirect_outsourcing.common.ResponseCodeEnum;
import com.sparta.redirect_outsourcing.domain.user.dto.ProfileLikesResponseDto;
import com.sparta.redirect_outsourcing.domain.user.entity.User;
import com.sparta.redirect_outsourcing.exception.custom.user.UserException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserAdapter {
    private final UserRepository userRepository;
    private final UserQueryRepository userQueryRepository;

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserException(ResponseCodeEnum.USER_NOT_FOUND));
    }

    public ProfileLikesResponseDto findByUserId(Long userId) {
        return userQueryRepository.findByUserId(userId);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserException(ResponseCodeEnum.USER_NOT_FOUND));
    }
    public User save(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findByKakaoId(Long kakaoId) {
        return userRepository.findByKakaoId(kakaoId);
    }

}