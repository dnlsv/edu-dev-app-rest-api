package com.project.services;

import com.project.forms.DeleteForm;
import com.project.forms.EditForm;
import com.project.forms.UserForm;
import com.project.transfer.UserDto;

public interface UserService {

  UserDto addUser(UserForm userForm);
  void deleteUser(DeleteForm deleteForm);
  void editUser(EditForm editForm);
}
