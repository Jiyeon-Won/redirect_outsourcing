package com.sparta.redirect_outsourcing.exception.custom.restaurant;

import com.sparta.redirect_outsourcing.common.ResponseCodeEnum;

public class CannotUnLikeOwnRestaurantException extends RestaurantException {
    public CannotUnLikeOwnRestaurantException(ResponseCodeEnum responseCodeEnum) {
        super(responseCodeEnum);
    }
}