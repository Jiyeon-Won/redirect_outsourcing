package com.sparta.redirect_outsourcing.domain.like.repository;

import com.sparta.redirect_outsourcing.common.ResponseCodeEnum;
import com.sparta.redirect_outsourcing.domain.like.dto.LikeResponseRestaurantDto;
import com.sparta.redirect_outsourcing.domain.like.dto.LikeResponseReviewDto;
import com.sparta.redirect_outsourcing.domain.like.entity.ContentsTypeEnum;
import com.sparta.redirect_outsourcing.domain.like.entity.Like;
import com.sparta.redirect_outsourcing.domain.user.entity.User;
import com.sparta.redirect_outsourcing.exception.custom.like.AlreadyLikedException;
import com.sparta.redirect_outsourcing.exception.custom.like.AlreadyUnLikedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class LikeAdapter {

    private final LikeQueryRepository likeQueryRepository;

    public Like saveLike(Like like) {
        return likeQueryRepository.save(like);
    }

    public void deleteLike(Long userId, Long contentsId, ContentsTypeEnum contentsType) {
        likeQueryRepository.deleteByUserIdAndReviewId(userId, contentsId, contentsType);
    }

    public void checkLikeExist(User user, Long contentsId, ContentsTypeEnum contentsType) {
        if (likeQueryRepository.existsByUserAndReview(user, contentsId, contentsType)) {
            throw new AlreadyLikedException(ResponseCodeEnum.ALREADY_LIKED);
        }
    }

    public void checkLikeNotExist(User user, Long contentsId, ContentsTypeEnum contentsType) {
        if (!likeQueryRepository.existsByUserAndReview(user, contentsId, contentsType)) {
            throw new AlreadyUnLikedException(ResponseCodeEnum.ALREADY_UN_LIKED);
        }
    }

    public List<LikeResponseRestaurantDto> findLikeRestaurantsWithPage(Long userId, Pageable pageable) {
        return likeQueryRepository.findLikeRestaurantsWithPage(userId, pageable);
    }

    public List<LikeResponseReviewDto> findLikeReviewsWithPage(Long userId, Pageable pageable) {
        return likeQueryRepository.findLikeReviewsWithPage(userId, pageable);
    }
}