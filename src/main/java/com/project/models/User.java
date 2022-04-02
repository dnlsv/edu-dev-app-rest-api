package com.project.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String firstName;

  private String lastName;

  private String login;

  private String hashPassword;

  @Enumerated(value = EnumType.STRING)
  private State state;

  @OneToMany(mappedBy = "user")
  private List<Token> tokens;
}
