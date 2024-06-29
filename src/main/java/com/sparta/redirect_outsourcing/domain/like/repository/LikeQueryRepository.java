package com.sparta.redirect_outsourcing.domain.like.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.redirect_outsourcing.domain.like.dto.LikeResponseRestaurantDto;
import com.sparta.redirect_outsourcing.domain.like.dto.LikeResponseReviewDto;
import com.sparta.redirect_outsourcing.domain.like.entity.ContentsTypeEnum;
import com.sparta.redirect_outsourcing.domain.like.entity.Like;
import com.sparta.redirect_outsourcing.domain.restaurant.entity.Restaurant;
import com.sparta.redirect_outsourcing.domain.review.entity.QReview;
import com.sparta.redirect_outsourcing.domain.review.entity.Review;
import com.sparta.redirect_outsourcing.domain.user.entity.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.sparta.redirect_outsourcing.domain.like.entity.QLike.like;
import static com.sparta.redirect_outsourcing.domain.restaurant.entity.QRestaurant.restaurant;
import static com.sparta.redirect_outsourcing.domain.review.entity.QReview.review;

@Slf4j
@Repository
@RequiredArgsConstructor
public class LikeQueryRepository {

    private final EntityManager em;
    private final JPAQueryFactory query;

    public Like save(Like like) {
        em.persist(like);
        return like;
    }

    public void deleteByUserIdAndReviewId(Long userId, Long contentsId, ContentsTypeEnum contentsType) {
        query.delete(like)
                .where(
                        like.user.id.eq(userId)
                                .and(like.contentsId.eq(contentsId))
                                .and(like.contentsType.eq(contentsType))
                ).execute();
    }

    public boolean existsByUserAndReview(User user, Long contentsId, ContentsTypeEnum contentsType) {
        return !query.selectFrom(like)
                .where(
                        like.user.eq(user)
                                .and(like.contentsId.eq(contentsId))
                                .and(like.contentsType.eq(contentsType))
                ).fetch().isEmpty();
    }

    public Like findLike(User user, Long contentsId, ContentsTypeEnum contentsType) {
        return null;
    }

    public List<LikeResponseRestaurantDto> findLikeRestaurantsWithPage(Long userId, Pageable pageable) {
        ContentsTypeEnum type = ContentsTypeEnum.RESTAURANT;

        List<Restaurant> fetch = query.selectFrom(restaurant)
                .join(like).on(like.contentsId.eq(restaurant.id))
                .where(like.user.id.eq(userId)
                        .and(like.contentsType.eq(type))
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return fetch.stream().map(LikeResponseRestaurantDto::new).toList();
    }

    public List<LikeResponseReviewDto> findLikeReviewsWithPage(Long userId, Pageable pageable) {
        ContentsTypeEnum type = ContentsTypeEnum.REVIEW;

        List<Review> fetch = query.selectFrom(review)
                .join(like).on(like.contentsId.eq(review.id))
                .where(like.user.id.eq(userId)
                        .and(like.contentsType.eq(type))
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return fetch.stream().map(LikeResponseReviewDto::new).toList();
    }
}