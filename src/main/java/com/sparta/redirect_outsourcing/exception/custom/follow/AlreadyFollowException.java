package com.sparta.redirect_outsourcing.exception.custom.follow;

import com.sparta.redirect_outsourcing.common.ResponseCodeEnum;

public class AlreadyFollowException extends FollowException {
    public AlreadyFollowException(ResponseCodeEnum responseCodeEnum) {
        super(responseCodeEnum);
    }
}