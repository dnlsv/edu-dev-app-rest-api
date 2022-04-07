package com.project.security.details;

import com.project.models.User;
import com.project.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UsersRepository usersRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> userCandidate = usersRepository.findOneByLogin(username);

    if (userCandidate.isPresent()) {
      return new UserDetailsImpl(userCandidate.get());
    } else throw new UsernameNotFoundException("User not found!");
  }
}
