package com.example.demo.entity;

import com.example.demo.dto.UserDto;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name="user")
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long seq;

  @Column(nullable = false)
  private String name;
  @Column(nullable = false)
  private int age;
  @Column(nullable = false, unique = true)
  private String email;
  @Column(nullable = false)
  private String passwd;

  public void updateDto(UserDto dto) {
    this.name = dto.getName();
    this.age = dto.getAge();
    this.passwd = dto.getPasswd();
  }
}
