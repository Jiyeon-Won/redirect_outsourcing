package com.sparta.redirect_outsourcing.domain.user.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ProfileLikesResponseDto {
    private String nickname;
    private String introduce;
    private String pictureUrl;
    private Long reviewLikeCount;
    private Long restaurantLikeCount;
}