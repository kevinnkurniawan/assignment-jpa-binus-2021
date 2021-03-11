package com.blibli.training.springframework.controller;

import com.blibli.training.springframework.converter.Converter;
import com.blibli.training.springframework.entity.User;
import com.blibli.training.springframework.request.UserRequest;
import com.blibli.training.springframework.response.UserResponse;
import com.blibli.training.springframework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tommy.setiawan on 3/8/2021.
 */

@RestController
@RequestMapping(path = "/users")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public List<UserResponse> findAll() {
    List<User> users = userService.findAll();
    return Converter.convertUsersToUserResponses(users);

  }

  @GetMapping("/firstName/{firstName}")
  public List<UserResponse> findByFirstName(@PathVariable String firstName) {
    List<User> user = userService.findByFirstName(firstName);
    return Converter.convertUsersToUserResponses(user);
  }

  @GetMapping("/{username}")
  public UserResponse findByUserName(@PathVariable String username) {
    User user = userService.findByUsername(username);
    return Converter.convertUserToUserResponse(user);
  }

  @PostMapping
  public UserResponse save(@RequestBody UserRequest request) {
    User user = Converter.convertUserRequestToUser(request);
    User savedUser = userService.save(user);
    return Converter.convertUserToUserResponse(userService.save(savedUser));
  }

}
