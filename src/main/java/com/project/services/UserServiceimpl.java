package com.project.services;

import com.project.forms.UserForm;
import com.project.models.State;
import com.project.models.User;
import com.project.repositories.UsersRepository;
import com.project.transfer.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceimpl implements UserService {

  @Autowired
  private UsersRepository usersRepository;

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
}
