package com.project.services;

import com.project.forms.LoginForm;
import com.project.models.Token;
import com.project.models.User;
import com.project.repositories.TokensRepository;
import com.project.repositories.UsersRepository;
import com.project.transfer.TokenDto;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

  @Autowired
  private UsersRepository usersRepository;

  @Autowired
  private TokensRepository tokensRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public TokenDto login(LoginForm loginForm) {
    Optional<User> userCandidate = usersRepository.findOneByLogin(loginForm.getLogin());

    if (userCandidate.isPresent()) {
      User user = userCandidate.get();
      if (passwordEncoder.matches(loginForm.getPassword(), user.getHashPassword())) {
        List<Token> oldTokens = tokensRepository.findAllByUser(user);
        tokensRepository.deleteAll(oldTokens);
        Token token = Token.builder()
                .user(user)
                .value(RandomStringUtils.random(10, true, true))
                .build();
        tokensRepository.save(token);
        return TokenDto.from(token);
      } throw new IllegalArgumentException("Incorrect login or password!");
    } throw new IllegalArgumentException("Incorrect login or password!");
  }
}
