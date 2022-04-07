package com.project.security.provider;

import com.project.models.Token;
import com.project.repositories.TokensRepository;
import com.project.security.token.TokenAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {

  @Autowired
  private TokensRepository tokensRepository;

  @Autowired
  private UserDetailsService userDetailsService;

  @Override
  public Authentication authenticate(Authentication authentication) {
    TokenAuthentication tokenAuthentication = (TokenAuthentication)authentication;
    Optional<Token> tokenCandidate = tokensRepository.findOneByValue(tokenAuthentication.getName());

    if (tokenCandidate.isPresent()) {
      UserDetails userDetails = userDetailsService.loadUserByUsername(tokenCandidate.get().getUser().getLogin());
      tokenAuthentication.setUserDetails(userDetails);
      tokenAuthentication.setAuthenticated(true);
      return tokenAuthentication;
    } else throw new IllegalArgumentException("Bad token!");
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return TokenAuthentication.class.equals(authentication);
  }
}
