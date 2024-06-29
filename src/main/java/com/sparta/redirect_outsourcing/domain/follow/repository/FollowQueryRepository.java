package com.sparta.redirect_outsourcing.domain.follow.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.redirect_outsourcing.domain.follow.entity.Follow;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Objects;

import static com.sparta.redirect_outsourcing.domain.follow.entity.QFollow.follow;

@Slf4j
@Repository
@RequiredArgsConstructor
public class FollowQueryRepository {
    private final EntityManager em;
    private final JPAQueryFactory query;

    public boolean existFollow(Long loginUserId, Long followUserId) {
        Follow findFollow = query.selectFrom(follow)
                .where(follow.followee.id.eq(loginUserId).and(follow.follower.id.eq(followUserId)))
                .fetchOne();
        return !Objects.isNull(findFollow);
    }

    public void addFollow(Follow follow) {
        em.persist(follow);
    }

    public void deleteById(Long loginUserId, Long followUserId) {
        query.delete(follow)
                .where(
                        follow.follower.id.eq(followUserId)
                                .and(follow.followee.id.eq(loginUserId))
                ).execute();
    }
}