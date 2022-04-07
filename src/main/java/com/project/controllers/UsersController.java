package com.project.controllers;

import com.project.forms.DeleteForm;
import com.project.forms.EditForm;
import com.project.forms.UserForm;
import com.project.models.User;
import com.project.services.UserService;
import com.project.transfer.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsersController {

  @Autowired
  private UserService userService;

  @PostMapping("/registration")
  public ResponseEntity<UserDto> addUser(@RequestBody UserForm userForm) {
    return ResponseEntity.ok(userService.addUser(userForm));
  }

  @DeleteMapping("/profile/delete-user")
  public ResponseEntity<Object> deleteUser(@RequestBody DeleteForm deleteForm) {
    userService.deleteUser(deleteForm);
    return ResponseEntity.ok().build();
  }

  @PutMapping("profile/edit-user")
  public ResponseEntity<Object> editUser(@RequestBody EditForm editForm) {
    userService.editUser(editForm);
    return ResponseEntity.ok().build();
  }
}
