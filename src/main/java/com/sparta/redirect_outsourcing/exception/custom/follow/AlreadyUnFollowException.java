package com.sparta.redirect_outsourcing.exception.custom.follow;

import com.sparta.redirect_outsourcing.common.ResponseCodeEnum;

public class AlreadyUnFollowException extends FollowException {
    public AlreadyUnFollowException(ResponseCodeEnum responseCodeEnum) {
        super(responseCodeEnum);
    }
}