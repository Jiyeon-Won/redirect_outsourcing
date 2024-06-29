package com.sparta.redirect_outsourcing.domain.like.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sparta.redirect_outsourcing.domain.restaurant.entity.Restaurant;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LikeResponseRestaurantDto {
    private Long id;
    private String name;
    private String address;
    private String category;
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    private int likeCount;

    public LikeResponseRestaurantDto(Restaurant restaurant) {
        this.id = restaurant.getId();
        this.name = restaurant.getName();
        this.address = restaurant.getAddress();
        this.category = restaurant.getCategory().toString();
        this.description = restaurant.getDescription();
        this.createdAt = restaurant.getCreatedAt();
        this.likeCount = restaurant.getLikeCount();
    }
}