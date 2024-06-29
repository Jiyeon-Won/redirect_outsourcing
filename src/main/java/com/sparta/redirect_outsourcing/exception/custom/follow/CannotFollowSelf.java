package com.sparta.redirect_outsourcing.exception.custom.follow;

import com.sparta.redirect_outsourcing.common.ResponseCodeEnum;

public class CannotFollowSelf extends FollowException {
    public CannotFollowSelf(ResponseCodeEnum responseCodeEnum) {
        super(responseCodeEnum);
    }
}