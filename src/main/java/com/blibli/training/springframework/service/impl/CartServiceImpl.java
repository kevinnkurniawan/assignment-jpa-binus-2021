package com.blibli.training.springframework.service.impl;

import com.blibli.training.springframework.entity.Cart;
import com.blibli.training.springframework.entity.CartItem;
import com.blibli.training.springframework.entity.User;
import com.blibli.training.springframework.repository.CartItemRepository;
import com.blibli.training.springframework.repository.CartRepository;
import com.blibli.training.springframework.service.CartService;
import com.blibli.training.springframework.service.UserService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by tommy.setiawan on 3/9/2021.
 */

@Service
public class CartServiceImpl implements CartService {

  private final CartRepository cartRepository;
  private final CartItemRepository cartItemRepository;
  private final UserService userService;

  @Autowired
  public CartServiceImpl(CartRepository cartRepository, CartItemRepository cartItemRepository, UserService userService) {
    this.cartRepository = cartRepository;
    this.cartItemRepository = cartItemRepository;
    this.userService = userService;
  }

  @Override
  public Optional<Cart> findById(Long id) {
    return cartRepository.findById(id);
  }

  @Override
  public Cart findByUsername(String username) {
    Cart cart = cartRepository.findByUser_Username(username);
    if(Objects.nonNull(cart)) {
      Hibernate.initialize(cart.getCartItems());
      Hibernate.initialize(cart.getUser());
    }
    return cart;
  }

  @Override
  public void addToCart(CartItem cartItem, String username) {
    Cart existingCart = cartRepository.findByUser_Username(username);
    if (Objects.isNull(existingCart)) {
      this.save(cartItem, username);
    } else {
      this.update(existingCart, cartItem, username);
    }
  }

  private void save(CartItem cartItem, String username) {
    User user = this.userService.findByUsername(username);
    if (Objects.isNull(user)) {
      return;
    }
    Cart cart = Cart.builder()
        .user(user)
        .addToCartTimestamp(new Date())
        .build();
    Cart savedCart = cartRepository.save(cart);
    cartItem.setCart(savedCart);
    cartItemRepository.save(cartItem);
  }

  private Cart update(Cart cart, CartItem cartItem, String username) {
    Cart existingCart = cartRepository.findByUser_Username(username);
    if (Objects.nonNull(existingCart)) {
      User user = this.userService.findByUsername(username);
      if (Objects.isNull(user)) {
        return null;
      }

      if(!CollectionUtils.isEmpty(existingCart.getCartItems())) {
        CartItem updatedCartItem = existingCart.getCartItems()
            .stream()
            .filter(existingCartItem -> existingCartItem.getItemName().equals(cartItem.getItemName()))
            .findFirst()
            .orElse(null);
        if(Objects.isNull(updatedCartItem)) {
          updatedCartItem = CartItem.builder()
              .cart(cart)
              .itemName(cartItem.getItemName())
              .quantity(cartItem.getQuantity())
              .build();
        } else {
          updatedCartItem.setQuantity(cartItem.getQuantity());
          updatedCartItem.setItemName(cartItem.getItemName());
        }
        cartItemRepository.save(updatedCartItem);
      }
    }
    return existingCart;
  }

  @Override
  public boolean delete(Long id) {
    Optional<Cart> existingCart = cartRepository.findById(id);
    if (!existingCart.isPresent()) {
      return false;
    } else {
      cartRepository.delete(existingCart.get());
    }
    return true;
  }
}
