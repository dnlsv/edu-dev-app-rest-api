package com.project.controllers;

import com.project.forms.UserForm;
import com.project.services.UserService;
import com.project.transfer.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {

  @Autowired
  private UserService userService;

  @PostMapping("/registration")
  public ResponseEntity<UserDto> addUser(@RequestBody UserForm userForm) {
    return ResponseEntity.ok(userService.addUser(userForm));
  }
}
