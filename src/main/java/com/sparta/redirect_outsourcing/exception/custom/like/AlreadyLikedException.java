package com.sparta.redirect_outsourcing.exception.custom.like;

import com.sparta.redirect_outsourcing.common.ResponseCodeEnum;

public class AlreadyLikedException extends LikeException {
    public AlreadyLikedException(ResponseCodeEnum responseCodeEnum) {
        super(responseCodeEnum);
    }
}