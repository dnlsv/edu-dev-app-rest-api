package com.project.services;

import com.project.forms.UserForm;
import com.project.transfer.UserDto;

public interface UserService {

  UserDto addUser(UserForm userForm);
}
