package com.sparta.redirect_outsourcing.domain.like.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sparta.redirect_outsourcing.domain.review.entity.Review;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LikeResponseReviewDto {
    private Long id;
    private Float rating;
    private String comment;
    private int likeCount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    public LikeResponseReviewDto(Review review) {
        this.id = review.getId();
        this.rating = review.getRating();
        this.comment = review.getComment();
        this.likeCount = review.getLikeCount();
        this.createdAt = review.getCreatedAt();
    }
}