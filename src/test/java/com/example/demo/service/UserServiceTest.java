package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("사용자 API TEST")
public class UserServiceTest {

  @InjectMocks
  private UserService userService;

  @Mock
  private UserRepository userRepository;

  @Test
  @DisplayName("모든 사용자 조회")
  void all() {
    given(userRepository.findAll()).willReturn(
        List.of(new UserEntity(),
            new UserEntity(),
            new UserEntity())
    );

    //when
    var users = userService.all();

    //then
    assertThat(users).isNotNull();
    assertThat(users.size()).isEqualTo(3);
    verify(userRepository, atLeastOnce()).findAll();
  }

  @Test
  @DisplayName("사용자 조회 (단건)")
  void user() {
    Long seq = 1L;
    given(userRepository.findById(seq))
        .willReturn(this.createUser());

    //when
    var user = userService.user(seq);

    //then
    assertThat(user).isNotNull();
    assertThat(user.getName()).isEqualTo("홍길동");
    verify(userRepository, atLeastOnce()).findById(seq);
  }

  @Test
  @DisplayName("사용자 정보 수정")
  void update() {

    final var userDto= UserDto.builder()
        .seq(1L)
        .age(12)
        .name("test")
        .passwd("12345AA!")
        .build();

    given(userRepository.findById(userDto.getSeq()))
        .willReturn(this.createUser());

    given(userRepository.save(any())).willReturn(
        UserEntity.builder()
            .seq(1L).name(userDto.getName())
            .age(userDto.getAge())
            .passwd(userDto.getPasswd())
            .email("test@gmail.com")
            .build()
    );

    //when
    var user = userService.update(userDto);

    //then
    assertThat(user).isNotNull();
    assertThat(user.getName()).isEqualTo(userDto.getName());
    assertThat(user.getPasswd()).isEqualTo(userDto.getPasswd());
    verify(userRepository, atLeastOnce()).findById(userDto.getSeq());
    verify(userRepository, atLeastOnce()).save(any());

  }

  @Test
  @DisplayName("이미 등록된 이메일 사용자 저장 실패")
  void saveFail() {
    given(userRepository.findByEmail(anyString()))
        .willReturn(this.createUser());

    //then
    assertThrows(IllegalArgumentException.class, () ->{
      userService.save(this.createUserEntity());
    });

    verify(userRepository, atLeastOnce()).findByEmail(anyString());
  }

  @Test
  @DisplayName("사용자 등록")
  void saveSuccess() {
    given(userRepository.findByEmail(anyString()))
        .willReturn(Optional.empty());
    given(userRepository.save(any())).willReturn(this.createUserEntity());

    //when
    var user = userService.save(this.createUserEntity());

    //then
    assertThat(user).isNotNull();
    verify(userRepository, atLeastOnce()).findByEmail(anyString());
    verify(userRepository, atLeastOnce()).save(any());
  }

  Optional<UserEntity> createUser() {
    return Optional.of(this.createUserEntity());
  }

  UserEntity createUserEntity() {
    return UserEntity.builder()
        .seq(1L).name("홍길동").age(20).email("test@gmail.com")
        .passwd("12345!")
        .build();
  }
}
