package com.project.repositories;

import com.project.models.Token;
import com.project.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TokenRepository extends JpaRepository<Token, Integer> {

  List<Token> findAllByUser(User user);
}
