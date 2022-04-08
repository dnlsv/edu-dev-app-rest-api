package com.project.services;

import com.project.forms.LoginForm;
import com.project.models.Token;

public interface LoginService {

  Token login(LoginForm loginForm);
}
