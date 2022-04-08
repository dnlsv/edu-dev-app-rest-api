package com.project.transfer;

import com.project.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {

  private String firstName;

  private String lastName;

  private int age;

  private String login;

  public static UserDto from(User user) {
    return new UserDto(user.getFirstName(), user.getLastName(), user.getAge(), user.getLogin());
  }
}
