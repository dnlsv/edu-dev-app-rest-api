package com.project.controllers;

import com.project.forms.LoginForm;
import com.project.services.LoginService;
import com.project.transfer.TokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

  @Autowired
  private LoginService loginService;

  @PostMapping("/login")
  public ResponseEntity<TokenDto> login(@RequestBody LoginForm loginForm) {
    return ResponseEntity.ok(TokenDto.from(loginService.login(loginForm)));
  }
}
