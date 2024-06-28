package com.sparta.redirect_outsourcing.domain.restaurant.repository;

import com.sparta.redirect_outsourcing.common.ResponseCodeEnum;
import com.sparta.redirect_outsourcing.domain.restaurant.entity.Restaurant;
import com.sparta.redirect_outsourcing.domain.user.entity.User;
import com.sparta.redirect_outsourcing.exception.custom.restaurant.CannotLikeOwnRestaurantException;
import com.sparta.redirect_outsourcing.exception.custom.restaurant.RestaurantNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class RestaurantAdapter {

    private final RestaurantRepository restaurantRepository;

    public Restaurant findById(Long restaurantId) {
        return restaurantRepository.findById(restaurantId).orElseThrow(() -> new RestaurantNotFoundException(ResponseCodeEnum.RESTAURANT_NOT_EXIST));
    }

    public Restaurant save(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public void delete(Restaurant restaurant) {
        restaurantRepository.delete(restaurant);
    }

    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    public void checkRestaurantOwner(User user, Long restaurantId) {
        Restaurant findRestaurant = findById(restaurantId);
        if (Objects.equals(findRestaurant.getUser().getId(), user.getId())) {
            throw new CannotLikeOwnRestaurantException(ResponseCodeEnum.LiKE_OWN_RESTAURANT);
        }
    }
}