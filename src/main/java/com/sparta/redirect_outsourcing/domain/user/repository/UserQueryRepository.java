package com.sparta.redirect_outsourcing.domain.user.repository;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.redirect_outsourcing.domain.like.entity.ContentsTypeEnum;
import com.sparta.redirect_outsourcing.domain.user.dto.ProfileLikesResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.sparta.redirect_outsourcing.domain.like.entity.QLike.like;
import static com.sparta.redirect_outsourcing.domain.user.entity.QUser.user;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserQueryRepository {

    private final JPAQueryFactory query;

    public ProfileLikesResponseDto findByUserId(Long userId) {

        List<Tuple> fetch = query.select(
                        user.nickname,
                        user.introduce,
                        user.pictureUrl,
                        like.contentsType,
                        like.id.count()
                )
                .from(like)
                .join(user).on(like.user.id.eq(user.id))
                .where(like.user.id.eq(userId))
                .groupBy(like.contentsType)
                .fetch();

        Map<ContentsTypeEnum, Long> collect = fetch.stream()
                .collect(Collectors.toMap(
                        tuple -> tuple.get(like.contentsType),
                        tuple -> tuple.get(like.id.count())
                ));

        log.info("리뷰 좋아요 수: {}", collect.get(ContentsTypeEnum.REVIEW));
        log.info("음식점 좋아요 수: {}", collect.get(ContentsTypeEnum.RESTAURANT));

        return new ProfileLikesResponseDto(
                fetch.get(0).get(user.nickname),
                fetch.get(0).get(user.introduce),
                fetch.get(0).get(user.pictureUrl),
                collect.get(ContentsTypeEnum.REVIEW),
                collect.get(ContentsTypeEnum.RESTAURANT)
        );
    }
}