package com.project.services;

import com.project.forms.ChangePasswordForm;
import com.project.forms.DeleteForm;
import com.project.forms.EditForm;
import com.project.forms.UserForm;
import com.project.models.User;

public interface UserService {

  User addUser(UserForm userForm);
  User getUser(String login);
  void deleteUser(DeleteForm deleteForm);
  User editUser(EditForm editForm);
  void changePassword(ChangePasswordForm changePasswordForm);
}
