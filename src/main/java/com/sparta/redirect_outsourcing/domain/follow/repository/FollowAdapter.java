package com.sparta.redirect_outsourcing.domain.follow.repository;

import com.sparta.redirect_outsourcing.domain.follow.entity.Follow;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
}