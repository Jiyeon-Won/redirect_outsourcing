package com.sparta.redirect_outsourcing.exception.custom.like;

import com.sparta.redirect_outsourcing.common.ResponseCodeEnum;

public class AlreadyUnLikedException extends LikeException {
    public AlreadyUnLikedException(ResponseCodeEnum responseCodeEnum) {
        super(responseCodeEnum);
    }
}