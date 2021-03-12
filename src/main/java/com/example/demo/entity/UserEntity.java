package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity(name="user")
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long seq;

  @Column(nullable = false)
  private String name;
  @Column(nullable = false)
  private int age;
  @Column(nullable = false)
  private String email;
  @Column(nullable = false)
  private String passwd;
}
