package com.sparta.redirect_outsourcing.domain.follow.repository;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.redirect_outsourcing.domain.follow.entity.Follow;
import com.sparta.redirect_outsourcing.domain.like.entity.ContentsTypeEnum;
import com.sparta.redirect_outsourcing.domain.restaurant.dto.responseDto.RestaurantResponseDto;
import com.sparta.redirect_outsourcing.domain.user.entity.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

import static com.sparta.redirect_outsourcing.domain.follow.entity.QFollow.follow;
import static com.sparta.redirect_outsourcing.domain.like.entity.QLike.like;
import static com.sparta.redirect_outsourcing.domain.restaurant.entity.QRestaurant.restaurant;
import static com.sparta.redirect_outsourcing.domain.user.entity.QUser.user;

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

    public List<RestaurantResponseDto> findByFollowerLikesRestaurant(User loginUser, Pageable pageable) {
        List<Tuple> fetch = query.select(
                        restaurant.id,
                        restaurant.name,
                        restaurant.address,
                        restaurant.category,
                        restaurant.description,
                        restaurant.createdAt,
                        restaurant.likeCount
                )
                .from(restaurant)
                .join(like).on(restaurant.id.eq(like.contentsId))
                .join(user).on(like.user.id.eq(user.id))
                .join(follow).on(user.id.eq(follow.follower.id))
                .where(
                        follow.followee.id.eq(loginUser.getId())
                                .and(like.contentsType.eq(ContentsTypeEnum.RESTAURANT))
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(
                        new OrderSpecifier<>(Order.DESC, restaurant.createdAt)
                )
                .fetch();

        return fetch.stream().map(tuple ->
            RestaurantResponseDto.builder()
                    .id(tuple.get(restaurant.id))
                    .name(tuple.get(restaurant.name))
                    .address(tuple.get(restaurant.address))
                    .category(tuple.get(restaurant.category).getCuisineType())
                    .description(tuple.get(restaurant.description))
                    .createdAt(tuple.get(restaurant.createdAt))
                    .likeCount(tuple.get(restaurant.likeCount))
                    .build()
        ).toList();
    }
}