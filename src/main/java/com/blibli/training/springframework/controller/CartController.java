package com.blibli.training.springframework.controller;

import com.blibli.training.springframework.converter.Converter;
import com.blibli.training.springframework.entity.Cart;
import com.blibli.training.springframework.entity.CartItem;
import com.blibli.training.springframework.request.CartItemRequest;
import com.blibli.training.springframework.response.BaseResponse;
import com.blibli.training.springframework.response.CartResponse;
import com.blibli.training.springframework.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tommy.setiawan on 3/9/2021.
 */

@RestController
@RequestMapping(path = "/carts")
public class CartController {

  private final CartService cartService;

  @Autowired
  public CartController(CartService cartService) {
    this.cartService = cartService;
  }

  @GetMapping("/{username}")
  public CartResponse findByUsername(@PathVariable String username) {
    Cart cart = cartService.findByUsername(username);
    return Converter.convertCartToCartResponse(cart);
  }

  @PostMapping("/addToCart/{username}")
  public BaseResponse addToCart(@RequestBody CartItemRequest request, @PathVariable String username) {
    CartItem cartItem = Converter.convertCartRequestToCart(request);
    this.cartService.addToCart(cartItem, username);
    return BaseResponse.builder()
        .success(true)
        .build();
  }

  @DeleteMapping("/{id}")
  public boolean delete(@PathVariable("id") long id) {
    cartService.delete(id);
    return true;
  }

}
