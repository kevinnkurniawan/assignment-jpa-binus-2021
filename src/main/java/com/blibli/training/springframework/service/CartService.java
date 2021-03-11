package com.blibli.training.springframework.service;


import com.blibli.training.springframework.entity.Cart;
import com.blibli.training.springframework.entity.CartItem;

import java.util.Optional;

/**
 * Created by tommy.setiawan on 3/9/2021.
 */
public interface CartService {
    Optional<Cart> findById(Long id);

    Cart findByUsername(String username);

    void addToCart(CartItem cartItem, String username);

    boolean delete(Long id);
}
