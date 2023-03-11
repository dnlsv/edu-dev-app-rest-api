package com.project.controllers;

import com.project.forms.ChangePasswordForm;
import com.project.forms.EditForm;
import com.project.forms.UniqueForm;
import com.project.forms.UserForm;
import com.project.services.UsersService;
import com.project.transfer.UserDto;
import com.project.transfer.UserLogDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsersController {

  @Autowired
  private UsersService usersService;

  @PostMapping("/registration")
  public ResponseEntity<UserLogDto> addUser(@RequestBody UserForm userForm) {
    return ResponseEntity.ok(UserLogDto.from(usersService.addUser(userForm)));
  }

  @GetMapping("/profile/get-user")
  public ResponseEntity<UserDto> getUser(@RequestParam String login) {
    return ResponseEntity.ok(UserDto.from(usersService.getUser(login)));
  }

  @DeleteMapping("/profile/delete-user")
  public ResponseEntity<Object> deleteUser(@RequestBody UniqueForm uniqueForm) {
    usersService.deleteUser(uniqueForm);
    return ResponseEntity.ok().build();
  }

  @PutMapping("profile/edit-user")
  public ResponseEntity<UserLogDto> editUser(@RequestBody EditForm editForm) {
    return ResponseEntity.ok(UserLogDto.from(usersService.editUser(editForm)));
  }

  @PutMapping("profile/change-password")
  public ResponseEntity<Object> changePassword(@RequestBody ChangePasswordForm changePasswordForm) {
    usersService.changePassword(changePasswordForm);
    return ResponseEntity.ok().build();
  }
}
