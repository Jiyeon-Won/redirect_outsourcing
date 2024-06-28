package com.sparta.redirect_outsourcing.exception.custom.like;

import com.sparta.redirect_outsourcing.common.ResponseCodeEnum;

public class NotFoundLikeException extends LikeException {
    public NotFoundLikeException(ResponseCodeEnum responseCodeEnum) {
        super(responseCodeEnum);
    }
}