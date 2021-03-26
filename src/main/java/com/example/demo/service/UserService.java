package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

  private final UserRepository userRepository;

  public List<UserEntity> all () {
    return userRepository.findAll();
  }

  public UserEntity user (final Long seq) {
    return userRepository.findById(seq).orElseThrow(IllegalAccessError::new);
  }

  public UserEntity update (UserDto user) {
    var entity =  userRepository.findById(user.getSeq())
        .map(userEntity -> {
          userEntity.updateDto(user);
          return userEntity;
        })
        .orElseThrow(IllegalAccessError::new);

    return userRepository.save(entity);
  }

  public UserEntity save(UserEntity userEntity) {
    var entity =  userRepository.findByEmail(userEntity.getEmail());
    if(entity.isPresent()) throw  new IllegalArgumentException("이미 등록된 이메일 입니다.");

    return userRepository.save(userEntity);
  }
}
