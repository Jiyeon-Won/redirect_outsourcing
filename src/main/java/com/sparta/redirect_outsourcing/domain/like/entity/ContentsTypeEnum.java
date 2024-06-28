package com.sparta.redirect_outsourcing.domain.like.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ContentsTypeEnum {
    REVIEW("review"),
    RESTAURANT("restaurant");
    private final String contentsType;
}