package com.project.services;

import com.project.forms.ChangePasswordForm;
import com.project.forms.UniqueForm;
import com.project.forms.EditForm;
import com.project.forms.UserForm;
import com.project.models.User;

public interface UsersService {

  User addUser(UserForm userForm);
  User getUser(String login);
  void deleteUser(UniqueForm uniqueForm);
  User editUser(EditForm editForm);
  void changePassword(ChangePasswordForm changePasswordForm);
}
