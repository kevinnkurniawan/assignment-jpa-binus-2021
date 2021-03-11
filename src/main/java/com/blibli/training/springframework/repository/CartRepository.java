package com.blibli.training.springframework.repository;

import com.blibli.training.springframework.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by tommy.setiawan on 3/9/2021.
 */

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findByUser_Username(String username);

    void delete(Cart cart);

    Cart save(Cart cart);
}
