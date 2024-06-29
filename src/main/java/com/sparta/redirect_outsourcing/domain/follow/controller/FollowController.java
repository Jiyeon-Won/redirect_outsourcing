package com.sparta.redirect_outsourcing.domain.follow.controller;

import com.sparta.redirect_outsourcing.auth.UserDetailsImpl;
import com.sparta.redirect_outsourcing.common.MessageResponseDto;
import com.sparta.redirect_outsourcing.common.ResponseUtils;
import com.sparta.redirect_outsourcing.domain.follow.service.FollowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/follow")
public class FollowController {

    public final FollowService followService;

    @PostMapping("/users/{followUserId}")
    public ResponseEntity<MessageResponseDto> addFollow(
            @AuthenticationPrincipal UserDetailsImpl loginUser,
            @PathVariable Long followUserId
    ) {
        followService.addFollow(loginUser.getUser(), followUserId);
        return ResponseUtils.of(HttpStatus.OK, "팔로우 성공");
    }

    @DeleteMapping("/users/{followUserId}")
    public ResponseEntity<MessageResponseDto> deleteFollow(
            @AuthenticationPrincipal UserDetailsImpl loginUser,
            @PathVariable Long followUserId
    ) {
        followService.deleteFollow(loginUser.getUser(), followUserId);
        return ResponseUtils.of(HttpStatus.OK, "팔로우 취소 성공");
    }
}