package com.blibli.training.springframework.converter;

import com.blibli.training.springframework.entity.Cart;
import com.blibli.training.springframework.entity.CartItem;
import com.blibli.training.springframework.entity.User;
import com.blibli.training.springframework.request.CartItemRequest;
import com.blibli.training.springframework.request.UserRequest;
import com.blibli.training.springframework.response.CartItemResponse;
import com.blibli.training.springframework.response.CartResponse;
import com.blibli.training.springframework.response.UserResponse;
import org.springframework.beans.BeanUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Converter {

  public static CartResponse convertCartToCartResponse(Cart cart) {
    CartResponse cartResponse = new CartResponse();
    BeanUtils.copyProperties(cart, cartResponse);

    UserResponse user = Converter.convertUserToUserResponse(cart.getUser());
    List<CartItemResponse> cartItems = Converter.convertCartItemsToCartItemResponses(cart.getCartItems());
    cartResponse.setUser(user);
    cartResponse.setCartItems(cartItems);
    return cartResponse;
  }

  public static CartItemResponse convertCartItemToCartItemResponse(CartItem cartItem) {
    CartItemResponse cartItemResponse = new CartItemResponse();
    BeanUtils.copyProperties(cartItem, cartItemResponse);
    return cartItemResponse;
  }

  public static List<CartItemResponse> convertCartItemsToCartItemResponses(Collection<CartItem> cartItems) {
    return cartItems.stream()
        .map(Converter::convertCartItemToCartItemResponse)
        .collect(Collectors.toList());
  }

  public static UserResponse convertUserToUserResponse(User user) {
    UserResponse userResponse = new UserResponse();
    BeanUtils.copyProperties(user, userResponse);
    return userResponse;
  }

  public static List<UserResponse> convertUsersToUserResponses(Collection<User> users) {
    return users.stream()
        .map(Converter::convertUserToUserResponse)
        .collect(Collectors.toList());
  }

  public static CartItem convertCartRequestToCart(CartItemRequest request) {
    CartItem cart = new CartItem();
    BeanUtils.copyProperties(request, cart);
    return cart;
  }

  public static User convertUserRequestToUser(UserRequest request) {
    User user = new User();
    BeanUtils.copyProperties(request, user);
    return user;
  }

}
