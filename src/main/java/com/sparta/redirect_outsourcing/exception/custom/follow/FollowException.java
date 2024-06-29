package com.sparta.redirect_outsourcing.exception.custom.follow;

import com.sparta.redirect_outsourcing.common.ResponseCodeEnum;
import lombok.Getter;

@Getter
public class FollowException extends RuntimeException {
    private final ResponseCodeEnum responseCodeEnum;
    public FollowException(ResponseCodeEnum responseCodeEnum){
        super(responseCodeEnum.getMessage());
        this.responseCodeEnum = responseCodeEnum;
    }
}