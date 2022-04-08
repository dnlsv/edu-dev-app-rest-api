package com.project.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String firstName;

  private String lastName;

  private int age;

  private String login;

  private String hashPassword;

  @Enumerated(value = EnumType.STRING)
  private State state;

  @OneToMany(mappedBy = "user")
  private List<Token> tokens;
}
