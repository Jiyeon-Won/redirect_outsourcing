package com.sparta.redirect_outsourcing.domain.like.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum ContentsTypeEnum {
    REVIEW("review"),
    RESTAURANT("restaurant");
    private final String contentsType;

    public static ContentsTypeEnum getByContentsType(String contentsType) {
        return Arrays.stream(values())
                .filter(contentsTypeEnum -> contentsTypeEnum.contentsType.equalsIgnoreCase(contentsType))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 컨텐츠 타입: " + contentsType));
    }
}