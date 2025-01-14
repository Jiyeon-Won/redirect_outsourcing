package com.sparta.redirect_outsourcing.domain.cart.repository;

import com.sparta.redirect_outsourcing.domain.cart.entity.Cart;
import com.sparta.redirect_outsourcing.domain.cart.entity.CartItem;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUsersId(Long usersId);

    void deleteByIdAndUsersId(Long id, Long userId);
}