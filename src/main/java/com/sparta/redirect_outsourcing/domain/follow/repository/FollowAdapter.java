package com.sparta.redirect_outsourcing.domain.follow.repository;

import com.sparta.redirect_outsourcing.domain.follow.entity.Follow;
import com.sparta.redirect_outsourcing.domain.restaurant.dto.responseDto.RestaurantResponseDto;
import com.sparta.redirect_outsourcing.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FollowAdapter {
    public final FollowQueryRepository followQueryRepository;

    public boolean existFollow(Long loginUserId, Long followUserId) {
        return followQueryRepository.existFollow(loginUserId, followUserId);
    }

    public void addFollow(Follow follow) {
        followQueryRepository.addFollow(follow);
    }

    public void deleteById(Long loginUserId, Long followUserId) {
        followQueryRepository.deleteById(loginUserId, followUserId);
    }

    public List<RestaurantResponseDto> findByFollowerLikesRestaurant(User loginUser, Pageable pageable, String sortBy, boolean isAsc) {
        return followQueryRepository.findByFollowerLikesRestaurant(loginUser, pageable, sortBy, isAsc);
    }
}