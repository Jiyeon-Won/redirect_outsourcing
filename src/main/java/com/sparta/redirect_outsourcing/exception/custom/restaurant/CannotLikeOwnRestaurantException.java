package com.sparta.redirect_outsourcing.exception.custom.restaurant;

import com.sparta.redirect_outsourcing.common.ResponseCodeEnum;
import com.sparta.redirect_outsourcing.exception.custom.like.LikeException;

public class CannotLikeOwnRestaurantException extends LikeException {
    public CannotLikeOwnRestaurantException(ResponseCodeEnum responseCodeEnum) {
        super(responseCodeEnum);
    }
}