package com.sparta.redirect_outsourcing.domain.like.controller;

import com.sparta.redirect_outsourcing.auth.UserDetailsImpl;
import com.sparta.redirect_outsourcing.common.MessageResponseDto;
import com.sparta.redirect_outsourcing.common.ResponseUtils;
import com.sparta.redirect_outsourcing.domain.like.dto.LikeRequestDto;
import com.sparta.redirect_outsourcing.domain.like.entity.ContentsTypeEnum;
import com.sparta.redirect_outsourcing.domain.like.service.LikeService;
import com.sparta.redirect_outsourcing.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/likes")
public class LikeController {
    private final LikeService likeService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<MessageResponseDto> addLikeReview(
            @AuthenticationPrincipal UserDetailsImpl loginUser,
            @Valid @RequestBody LikeRequestDto requestDto
            ) {
        likeService.addLike(loginUser.getUser(), requestDto);
        return ResponseUtils.of(HttpStatus.OK, "좋아요 등록 성공");
    }

    @DeleteMapping
    public ResponseEntity<MessageResponseDto> removeLikeReview(
            @AuthenticationPrincipal UserDetailsImpl loginUser,
            @Valid @RequestBody LikeRequestDto requestDto
    ) {
        likeService.removeLike(loginUser.getUser(), requestDto);
        return ResponseUtils.of(HttpStatus.OK, "좋아요 삭제 성공");
    }
}