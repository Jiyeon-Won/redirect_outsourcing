package com.sparta.redirect_outsourcing.domain.like.repository;

import com.sparta.redirect_outsourcing.common.ResponseCodeEnum;
import com.sparta.redirect_outsourcing.domain.like.dto.LikeRequestDto;
import com.sparta.redirect_outsourcing.domain.like.entity.ContentsTypeEnum;
import com.sparta.redirect_outsourcing.domain.like.entity.Like;
import com.sparta.redirect_outsourcing.domain.review.entity.Review;
import com.sparta.redirect_outsourcing.domain.user.entity.User;
import com.sparta.redirect_outsourcing.exception.custom.like.AlreadyLikedException;
import com.sparta.redirect_outsourcing.exception.custom.like.AlreadyUnLikedException;
import com.sparta.redirect_outsourcing.exception.custom.like.NotFoundLikeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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
}