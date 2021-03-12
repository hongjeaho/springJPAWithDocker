package com.example.demo.controller;

import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

  private final UserRepository userRepository;

  @GetMapping("/all")
  public ResponseEntity<List<UserEntity>> allUser() {
    return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
  }
}
