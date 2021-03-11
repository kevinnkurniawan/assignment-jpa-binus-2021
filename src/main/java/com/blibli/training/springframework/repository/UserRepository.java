package com.blibli.training.springframework.repository;

import com.blibli.training.springframework.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();

    List<User> findByFirstName(String firstName);

    User findByUsername(String username);

    User save(User user);
}
