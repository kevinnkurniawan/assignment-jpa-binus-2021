package com.blibli.training.springframework.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartResponse {

  Long id;
  Date addToCartTimestamp;
  List<CartItemResponse> cartItems = new ArrayList<>();
  UserResponse user;

}
