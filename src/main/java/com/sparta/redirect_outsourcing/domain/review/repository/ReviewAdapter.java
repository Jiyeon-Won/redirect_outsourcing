package com.sparta.redirect_outsourcing.domain.review.repository;

import com.sparta.redirect_outsourcing.common.ResponseCodeEnum;
import com.sparta.redirect_outsourcing.domain.review.entity.Review;
import com.sparta.redirect_outsourcing.exception.custom.review.ReviewNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReviewAdapter {
    private final ReviewQueryRepository reviewQueryRepository;

    public Review save(Review review) {
        return reviewQueryRepository.save(review);
    }

    public void delete(Review review){
        reviewQueryRepository.delete(review.getId());
    }

    public Review findById(Long reviewId){
        Review findReview = reviewQueryRepository.findById(reviewId);
        if (findReview == null) {
            throw new ReviewNotFoundException(ResponseCodeEnum.REVIEW_NOT_FOUND);
        }
        return findReview;
    }

    public List<Review> findByRestaurantId(Long restaurantId){
        return reviewQueryRepository.findAllByRestaurantId(restaurantId);
    }

}