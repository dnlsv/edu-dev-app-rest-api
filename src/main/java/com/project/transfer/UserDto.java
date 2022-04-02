package com.project.transfer;

import com.project.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {

  private String login;

  public static UserDto from(User user) {
    return new UserDto(user.getLogin());
  }
}
