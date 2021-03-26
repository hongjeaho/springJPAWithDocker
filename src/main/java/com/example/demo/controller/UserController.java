package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

  private final UserService userService;

  @GetMapping("/all")
  public ResponseEntity<List<UserEntity>> all() {
    return new ResponseEntity<>(userService.all(), HttpStatus.OK);
  }

  @GetMapping("/{seq}")
  public ResponseEntity<UserEntity> user(final @PathVariable Long seq) {
    return new ResponseEntity<>(userService.user(seq), HttpStatus.OK);
  }

  @PutMapping
  public ResponseEntity<UserEntity> update(final @RequestBody UserDto userDto) {
    return new ResponseEntity<>(userService.update(userDto), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<UserEntity> save(final @RequestBody UserEntity userEntity) {
    return new ResponseEntity<>(userService.save(userEntity), HttpStatus.OK);
  }
}
