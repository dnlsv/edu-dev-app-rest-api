package com.project.services;

import com.project.forms.ChangePasswordForm;
import com.project.forms.DeleteForm;
import com.project.forms.EditForm;
import com.project.forms.UserForm;
import com.project.models.State;
import com.project.models.Token;
import com.project.models.User;
import com.project.repositories.TokensRepository;
import com.project.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceimpl implements UserService {

  @Autowired
  private UsersRepository usersRepository;

  @Autowired
  private TokensRepository tokensRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public User addUser(UserForm userForm) {
    Optional<User> userCandidate = usersRepository.findOneByLogin(userForm.getLogin());

    if (userCandidate.isEmpty()) {
      String hashPassword = passwordEncoder.encode(userForm.getPassword());

      User user = User.builder()
              .firstName(userForm.getFirstName())
              .lastName(userForm.getLastName())
              .age(userForm.getAge())
              .login(userForm.getLogin())
              .hashPassword(hashPassword)
              .state(State.ACTIVE)
              .build();

      usersRepository.save(user);
      return user;
    } else throw new IllegalArgumentException("Login already exists!");
  }

  @Override
  public User getUser(String login) {
    Optional<User> userCandidate = usersRepository.findOneByLogin(login);

    if (userCandidate.isPresent()) {
      return userCandidate.get();
    } else throw new IllegalArgumentException("User not found!");
  }

  @Override
  public void deleteUser(DeleteForm deleteForm) {
    Optional<User> userCandidate = usersRepository.findOneByLogin(deleteForm.getLogin());

    if (userCandidate.isPresent()) {
      User user = userCandidate.get();
      List<Token> oldTokens = tokensRepository.findAllByUser(user);
      tokensRepository.deleteAll(oldTokens);

      usersRepository.delete(user);
    } else throw new IllegalArgumentException("User not found!");
  }

  @Override
  public User editUser(EditForm editForm) {
    Optional<User> userCandidate = usersRepository.findOneByLogin(editForm.getOldLogin());

    if (userCandidate.isPresent()) {
      if (usersRepository.findOneByLogin(editForm.getNewLogin()).isEmpty() ||
              editForm.getNewLogin().equals(editForm.getOldLogin())) {
        User user = userCandidate.get();
        User updatedUser = User.builder()
                .id(user.getId())
                .firstName(editForm.getFirstName())
                .lastName(editForm.getLastName())
                .age(editForm.getAge())
                .login(editForm.getNewLogin())
                .hashPassword(user.getHashPassword())
                .state(user.getState())
                .build();

        usersRepository.save(updatedUser);
        return updatedUser;
      } else throw new IllegalArgumentException("Login already exists!");
    } else throw new IllegalArgumentException("User not found!");
  }

  @Override
  public void changePassword(ChangePasswordForm changePasswordForm) {
    Optional<User> userCandidate = usersRepository.findOneByLogin(changePasswordForm.getLogin());

    if (userCandidate.isPresent()) {
      User user = userCandidate.get();

      String hashPassword = passwordEncoder.encode(changePasswordForm.getPassword());
      user.setHashPassword(hashPassword);

      usersRepository.save(user);
    } else throw new IllegalArgumentException("User not found!");
  }
}
