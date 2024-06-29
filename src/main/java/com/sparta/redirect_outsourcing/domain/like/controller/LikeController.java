package com.sparta.redirect_outsourcing.domain.like.controller;

import com.sparta.redirect_outsourcing.auth.UserDetailsImpl;
import com.sparta.redirect_outsourcing.common.DataResponseDto;
import com.sparta.redirect_outsourcing.common.MessageResponseDto;
import com.sparta.redirect_outsourcing.common.ResponseUtils;
import com.sparta.redirect_outsourcing.domain.like.dto.LikeRequestDto;
import com.sparta.redirect_outsourcing.domain.like.dto.LikeResponseRestaurantDto;
import com.sparta.redirect_outsourcing.domain.like.dto.LikeResponseReviewDto;
import com.sparta.redirect_outsourcing.domain.like.service.LikeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/likes")
public class LikeController {

    private final LikeService likeService;

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

    @GetMapping("/restaurant")
    public ResponseEntity<DataResponseDto<List<LikeResponseRestaurantDto>>> findLikeRestaurantsWithPage(
            @AuthenticationPrincipal UserDetailsImpl loginUser,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "5") int size,
            @RequestParam(required = false, defaultValue = "createdAt") String sortBy,
            @RequestParam(required = false, defaultValue = "false") boolean isAsc
    ) {
        List<LikeResponseRestaurantDto> responseDtoList = likeService.findLikeRestaurantsWithPage(loginUser.getUser(), page, size, sortBy, isAsc);
        return ResponseUtils.of(HttpStatus.OK, "좋아요 목록 조회 성공", responseDtoList);
    }

    @GetMapping("/review")
    public ResponseEntity<DataResponseDto<List<LikeResponseReviewDto>>> findLikeReviewsWithPage(
            @AuthenticationPrincipal UserDetailsImpl loginUser,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "5") int size,
            @RequestParam(required = false, defaultValue = "createdAt") String sortBy,
            @RequestParam(required = false, defaultValue = "false") boolean isAsc
    ) {
        List<LikeResponseReviewDto> responseDtoList = likeService.findLikeReviewsWithPage(loginUser.getUser(), page, size, sortBy, isAsc);
        return ResponseUtils.of(HttpStatus.OK, "좋아요 목록 조회 성공", responseDtoList);
    }
}