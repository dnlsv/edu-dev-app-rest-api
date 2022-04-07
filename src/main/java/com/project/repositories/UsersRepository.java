package com.project.repositories;

import com.project.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Integer> {

  Optional<User> findOneByLogin(String login);
}
