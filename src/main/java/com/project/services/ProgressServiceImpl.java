package com.project.services;

import com.project.forms.ProgressForm;
import com.project.forms.UniqueForm;
import com.project.models.Progress;
import com.project.models.User;
import com.project.repositories.ProgressRepository;
import com.project.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProgressServiceImpl implements ProgressService{

  private final int STARS_TO_LEVEL_UP = 100;

  @Autowired
  private ProgressRepository progressRepository;

  @Autowired
  private UsersRepository usersRepository;

  @Override
  public void addProgress(ProgressForm progressForm) {
    Optional<User> userCandidate = usersRepository.findOneByLogin(progressForm.getLogin());

    if (userCandidate.isPresent()) {
      Optional<Progress> progressCandidate = progressRepository.findOneByUser(userCandidate.get());

      if (progressCandidate.isPresent()) {
        Progress progress = progressCandidate.get();
        int level = progress.getLevel();
        int stars = progress.getStars() + progressForm.getStars();
        if (stars == STARS_TO_LEVEL_UP) {
          level++;
        }
        int completedTasks = progress.getCompletedTasks() + progressForm.getCompletedTasks();

        Progress updatedProgress = Progress.builder()
                .id(progressCandidate.get().getId())
                .level(level)
                .stars(stars)
                .completedTasks(completedTasks)
                .user(userCandidate.get())
                .build();

        progressRepository.save(updatedProgress);

      } else throw new IllegalArgumentException("Progress not found!");
    } else throw new IllegalArgumentException("User not found!");
  }

  @Override
  public Progress getProgress(String login) {
    Optional<User> userCandidate = usersRepository.findOneByLogin(login);

    if (userCandidate.isPresent()) {
      Optional<Progress> progressCandidate = progressRepository.findOneByUser(userCandidate.get());

      if (progressCandidate.isPresent()) {
        return progressCandidate.get();
      } else throw new IllegalArgumentException("Progress not found!");
    } else throw new IllegalArgumentException("User not found!");
  }
}
