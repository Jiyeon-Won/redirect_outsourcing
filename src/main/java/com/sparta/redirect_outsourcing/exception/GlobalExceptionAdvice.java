package com.sparta.redirect_outsourcing.exception;

import com.sparta.redirect_outsourcing.common.DataResponseDto;
import com.sparta.redirect_outsourcing.common.MessageResponseDto;
import com.sparta.redirect_outsourcing.common.ResponseUtils;
import com.sparta.redirect_outsourcing.exception.custom.like.LikeException;
import com.sparta.redirect_outsourcing.exception.custom.menu.MenuException;
import com.sparta.redirect_outsourcing.exception.custom.order.OrderException;
import com.sparta.redirect_outsourcing.exception.custom.restaurant.NotYourRestaurantException;
import com.sparta.redirect_outsourcing.exception.custom.restaurant.RestaurantException;
import com.sparta.redirect_outsourcing.exception.custom.review.ReviewException;
import com.sparta.redirect_outsourcing.exception.custom.user.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<MessageResponseDto> handleUserException(UserException e) {
        log.error("에러 메세지: ", e);
        return ResponseUtils.of(e.getResponseCodeEnum());
    }

    @ExceptionHandler(OrderException.class)
    public ResponseEntity<MessageResponseDto> handleOrderException(OrderException e) {
        log.error("에러 메세지: ", e);
        return ResponseUtils.of(e.getResponseCodeEnum());
    }

    @ExceptionHandler(ReviewException.class)
    public ResponseEntity<MessageResponseDto> handleReviewException(ReviewException e) {
        log.error("에러 메세지: ", e);
        return ResponseUtils.of(e.getResponseCodeEnum());
    }

    @ExceptionHandler(MenuException.class)
    public ResponseEntity<MessageResponseDto> handleMenuException(MenuException e) {
        log.error("에러 메세지: ", e);
        return ResponseUtils.of(e.getResponseCodeEnum());
    }

    @ExceptionHandler(RestaurantException.class)
    public ResponseEntity<MessageResponseDto> handleRestaurantException(RestaurantException e) {
        log.error("에러 메세지: ", e);
        return ResponseUtils.of(e.getResponseCodeEnum());
    }

    @ExceptionHandler(LikeException.class)
    public ResponseEntity<MessageResponseDto> handleLikeException(LikeException e) {
        log.error("에러 메세지", e);
        return ResponseUtils.of(e.getResponseCodeEnum());
    }

    @ExceptionHandler(NotYourRestaurantException.class)
    public ResponseEntity<MessageResponseDto> handleNotYourRestaurantException(NotYourRestaurantException e) {
        log.error("에러 메세지: ", e);
        return ResponseUtils.of(e.getResponseCodeEnum().getHttpStatus(),e.getMessage());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DataResponseDto<List<String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<String> errorMessageList = new ArrayList<>();
        e.getBindingResult().getAllErrors().forEach(v -> errorMessageList.add(v.getDefaultMessage()));
        log.error("유효성 검사 실패:\n\n{}", String.join(",\n", errorMessageList));
        return ResponseUtils.of(HttpStatus.BAD_REQUEST, "유효성 검사 실패", errorMessageList);
    }
}