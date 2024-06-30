package com.sparta.redirect_outsourcing.domain.follow.service;

import com.sparta.redirect_outsourcing.common.PageUtils;
import com.sparta.redirect_outsourcing.common.ResponseCodeEnum;
import com.sparta.redirect_outsourcing.domain.follow.entity.Follow;
import com.sparta.redirect_outsourcing.domain.follow.repository.FollowAdapter;
import com.sparta.redirect_outsourcing.domain.restaurant.dto.responseDto.RestaurantResponseDto;
import com.sparta.redirect_outsourcing.domain.user.entity.User;
import com.sparta.redirect_outsourcing.domain.user.repository.UserAdapter;
import com.sparta.redirect_outsourcing.exception.custom.follow.AlreadyFollowException;
import com.sparta.redirect_outsourcing.exception.custom.follow.AlreadyUnFollowException;
import com.sparta.redirect_outsourcing.exception.custom.follow.CannotFollowSelf;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FollowService {
    private final FollowAdapter followAdapter;
    private final UserAdapter userAdapter;

    @Transactional
    public void addFollow(User loginUser, Long followUserId) {
        checkFollowSelf(loginUser, followUserId);
        existFollow(loginUser, followUserId);

        User findUser = userAdapter.findById(followUserId);

        Follow follow = Follow.builder()
                .follower(findUser)
                .followee(loginUser)
                .build();
        followAdapter.addFollow(follow);
    }

    @Transactional
    public void deleteFollow(User loginUser, Long followUserId) {
        checkFollowSelf(loginUser, followUserId);
        notExistFollow(loginUser, followUserId);

        followAdapter.deleteById(loginUser.getId(), followUserId);
    }

    @Transactional(readOnly = true)
    public List<RestaurantResponseDto> findByFollowerLikesRestaurant(User loginUser, int page, int size, String sortBy, boolean isAsc) {
        Pageable pageable = PageUtils.of(page, size, sortBy, isAsc);
        List<RestaurantResponseDto> responseDtoList = followAdapter.findByFollowerLikesRestaurant(loginUser, pageable);
        return responseDtoList;
    }

    private void existFollow(User loginUser, Long followUserId) {
        if (followAdapter.existFollow(loginUser.getId(), followUserId)) {
            throw new AlreadyFollowException(ResponseCodeEnum.ALREADY_FOLLOWED);
        }
    }

    private void notExistFollow(User loginUser, Long followUserId) {
        if (!followAdapter.existFollow(loginUser.getId(), followUserId)) {
            throw new AlreadyUnFollowException(ResponseCodeEnum.ALREADY_UN_FOLLOWED);
        }
    }

    private void checkFollowSelf(User loginUser, Long followUserId) {
        if (Objects.equals(loginUser.getId(), followUserId)) {
            throw new CannotFollowSelf(ResponseCodeEnum.FOLLOW_SELF);
        }
    }
}