package com.sparta.redirect_outsourcing.domain.review.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.redirect_outsourcing.domain.review.entity.Review;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.sparta.redirect_outsourcing.domain.review.entity.QReview.review;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ReviewQueryRepository {

    private final EntityManager em;
    private final JPAQueryFactory query;

    public Review save(Review review) {
        em.persist(review);
        return review;
    }

    public void delete(Long reviewId) {
        query.delete(review)
                .where(review.id.eq(reviewId))
                .execute();
    }

    public Review findById(Long reviewId) {
        return query.selectFrom(review)
                .where(review.id.eq(reviewId))
                .fetchOne();
    }

    public List<Review> findAllByRestaurantId(Long restaurantId) {
        return query.selectFrom(review)
                .where(review.restaurant.id.eq(restaurantId))
                .fetch();
    }
}