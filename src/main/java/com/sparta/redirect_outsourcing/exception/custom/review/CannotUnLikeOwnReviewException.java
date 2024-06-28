package com.sparta.redirect_outsourcing.exception.custom.review;

import com.sparta.redirect_outsourcing.common.ResponseCodeEnum;

public class CannotUnLikeOwnReviewException extends ReviewException {
    public CannotUnLikeOwnReviewException(ResponseCodeEnum responseCodeEnum) {
        super(responseCodeEnum);
    }
}