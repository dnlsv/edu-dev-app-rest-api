package com.project.services;

import com.project.forms.LoginForm;
import com.project.transfer.TokenDto;

public interface LoginService {

  TokenDto login(LoginForm loginForm);
}
