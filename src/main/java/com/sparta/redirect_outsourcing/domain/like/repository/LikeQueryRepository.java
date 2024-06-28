package com.sparta.redirect_outsourcing.domain.like.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.redirect_outsourcing.domain.like.entity.ContentsTypeEnum;
import com.sparta.redirect_outsourcing.domain.like.entity.Like;
import com.sparta.redirect_outsourcing.domain.user.entity.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import static com.sparta.redirect_outsourcing.domain.like.entity.QLike.like;

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
}