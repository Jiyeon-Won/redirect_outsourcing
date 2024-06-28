package com.sparta.redirect_outsourcing.exception.custom.like;

import com.sparta.redirect_outsourcing.common.ResponseCodeEnum;

public class CannotLikeOwnReviewException extends LikeException {
    public CannotLikeOwnReviewException(ResponseCodeEnum responseCodeEnum) {
        super(responseCodeEnum);
    }
}