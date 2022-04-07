package com.project.services;

import com.project.forms.DeleteForm;
import com.project.forms.EditForm;
import com.project.forms.UserForm;
import com.project.models.State;
import com.project.models.Token;
import com.project.models.User;
import com.project.repositories.TokensRepository;
import com.project.repositories.UsersRepository;
import com.project.transfer.UserDto;
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
  public UserDto addUser(UserForm userForm) {
    Optional<User> userCandidate = usersRepository.findOneByLogin(userForm.getLogin());

    if (userCandidate.isEmpty()) {
      String hashPassword = passwordEncoder.encode(userForm.getPassword());

      User user = User.builder()
              .firstName(userForm.getFirstName())
              .lastName(userForm.getLastName())
              .login(userForm.getLogin())
              .hashPassword(hashPassword)
              .state(State.ACTIVE)
              .build();

      usersRepository.save(user);
      return UserDto.from(user);
    } throw new IllegalArgumentException("Login already exists!");
  }

  @Override
  public void deleteUser(DeleteForm deleteForm) {
    Optional<User> userCandidate = usersRepository.findOneByLogin(deleteForm.getLogin());

    if (userCandidate.isPresent()) {
      User user = userCandidate.get();
      List<Token> oldTokens = tokensRepository.findAllByUser(user);
      tokensRepository.deleteAll(oldTokens);

      usersRepository.delete(user);
    } throw new IllegalArgumentException("User not found!");
  }

  @Override
  public void editUser(EditForm editForm) {
    Optional<User> userCandidate = usersRepository.findOneByLogin(editForm.getOldLogin());

    if (userCandidate.isPresent()) {
      String hashPassword = passwordEncoder.encode(editForm.getPassword());
      User user = userCandidate.get();
      User updatedUser = User.builder()
              .id(user.getId())
              .firstName(editForm.getFirstName())
              .lastName(editForm.getLastName())
              .login(editForm.getNewLogin())
              .hashPassword(hashPassword)
              .state(user.getState())
              .build();

      usersRepository.save(updatedUser);
    } else throw new IllegalArgumentException("User not found!");
  }
}
