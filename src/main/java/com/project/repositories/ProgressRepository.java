package com.project.repositories;

import com.project.models.Progress;
import com.project.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProgressRepository extends JpaRepository<Progress, Integer> {
  Optional<Progress> findOneByUser(User user);
}
