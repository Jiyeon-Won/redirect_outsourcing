package com.sparta.redirect_outsourcing.exception.custom.review;

import com.sparta.redirect_outsourcing.common.ResponseCodeEnum;

public class ReviewNotFoundException extends ReviewException {
    public ReviewNotFoundException(ResponseCodeEnum responseCodeEnum) {
        super(responseCodeEnum);
    }
}