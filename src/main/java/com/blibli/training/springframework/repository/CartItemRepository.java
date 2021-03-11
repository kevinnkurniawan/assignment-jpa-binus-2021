package com.blibli.training.springframework.repository;

import com.blibli.training.springframework.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, String> {

  List<CartItem> findByCart_Id(Long cartId);
}
