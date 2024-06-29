package com.sparta.redirect_outsourcing.domain.like.service;

import com.sparta.redirect_outsourcing.common.PageUtils;
import com.sparta.redirect_outsourcing.common.ResponseCodeEnum;
import com.sparta.redirect_outsourcing.domain.like.dto.LikeRequestDto;
import com.sparta.redirect_outsourcing.domain.like.dto.LikeResponseRestaurantDto;
import com.sparta.redirect_outsourcing.domain.like.dto.LikeResponseReviewDto;
import com.sparta.redirect_outsourcing.domain.like.entity.ContentsTypeEnum;
import com.sparta.redirect_outsourcing.domain.like.entity.Like;
import com.sparta.redirect_outsourcing.domain.like.repository.LikeAdapter;
import com.sparta.redirect_outsourcing.domain.restaurant.dto.responseDto.RestaurantResponseDto;
import com.sparta.redirect_outsourcing.domain.restaurant.entity.Restaurant;
import com.sparta.redirect_outsourcing.domain.restaurant.repository.RestaurantAdapter;
import com.sparta.redirect_outsourcing.domain.review.entity.Review;
import com.sparta.redirect_outsourcing.domain.review.repository.ReviewAdapter;
import com.sparta.redirect_outsourcing.domain.user.entity.User;
import com.sparta.redirect_outsourcing.domain.user.repository.UserAdapter;
import com.sparta.redirect_outsourcing.exception.custom.like.CannotLikeOwnReviewException;
import com.sparta.redirect_outsourcing.exception.custom.restaurant.CannotLikeOwnRestaurantException;
import com.sparta.redirect_outsourcing.exception.custom.restaurant.CannotUnLikeOwnRestaurantException;
import com.sparta.redirect_outsourcing.exception.custom.review.CannotUnLikeOwnReviewException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeAdapter likeAdapter;
    private final ReviewAdapter reviewAdapter;
    private final RestaurantAdapter restaurantAdapter;
    private final UserAdapter userAdapter;

    @Transactional
    public void  addLike(User user, LikeRequestDto requestDto) {
        likeAdapter.checkLikeExist(user, requestDto.getContentsId(), requestDto.getContentsType());

        if (requestDto.getContentsType() == ContentsTypeEnum.REVIEW) {
            Review findReview = reviewAdapter.findById(requestDto.getContentsId());

            if (Objects.equals(findReview.getUser().getId(), user.getId())) {
                throw new CannotLikeOwnReviewException(ResponseCodeEnum.LiKE_OWN_REVIEW);
            }

            findReview.plusLikeCount();
        } else if (requestDto.getContentsType() == ContentsTypeEnum.RESTAURANT) {
            Restaurant findRestaurant = restaurantAdapter.findById(requestDto.getContentsId());

            if (Objects.equals(findRestaurant.getUser().getId(), user.getId())) {
                throw new CannotLikeOwnRestaurantException(ResponseCodeEnum.LiKE_OWN_RESTAURANT);
            }

            findRestaurant.plusLikeCount();
        }

        Like like = Like.of(user, requestDto);
        Like savedLike = likeAdapter.saveLike(like);
    }

    @Transactional
    public void removeLike(User user, LikeRequestDto requestDto) {
        likeAdapter.checkLikeNotExist(user, requestDto.getContentsId(), requestDto.getContentsType());

        if (requestDto.getContentsType() == ContentsTypeEnum.REVIEW) {
            Review findReview = reviewAdapter.findById(requestDto.getContentsId());

            if (Objects.equals(findReview.getUser().getId(), user.getId())) {
                throw new CannotUnLikeOwnReviewException(ResponseCodeEnum.LiKE_OWN_REVIEW);
            }

            findReview.minusLikeCount();
        } else if (requestDto.getContentsType() == ContentsTypeEnum.RESTAURANT) {
            Restaurant findRestaurant = restaurantAdapter.findById(requestDto.getContentsId());

            if (Objects.equals(findRestaurant.getUser().getId(), user.getId())) {
                throw new CannotUnLikeOwnRestaurantException(ResponseCodeEnum.LiKE_OWN_RESTAURANT);
            }

            findRestaurant.minusLikeCount();
        }

        likeAdapter.deleteLike(user.getId(), requestDto.getContentsId(), requestDto.getContentsType());
    }

    @Transactional(readOnly = true)
    public List<LikeResponseRestaurantDto> findLikeRestaurantsWithPage(User user, int page, int size, String sortBy, boolean isAsc) {
        Pageable pageable = PageUtils.of(page, size, sortBy, isAsc);

        return likeAdapter.findLikeRestaurantsWithPage(user.getId(), pageable);
    }

    @Transactional(readOnly = true)
    public List<LikeResponseReviewDto> findLikeReviewsWithPage(User user, int page, int size, String sortBy, boolean isAsc) {
        Pageable pageable = PageUtils.of(page, size, sortBy, isAsc);

        return likeAdapter.findLikeReviewsWithPage(user.getId(), pageable);
    }
}