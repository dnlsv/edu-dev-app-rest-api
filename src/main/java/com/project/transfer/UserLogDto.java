package com.project.transfer;

import com.project.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLogDto {

  private String login;

  public static UserLogDto from(User user) {
    return new UserLogDto(user.getLogin());
  }
}
