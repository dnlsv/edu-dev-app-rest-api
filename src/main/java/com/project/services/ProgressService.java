package com.project.services;

import com.project.forms.ProgressForm;
import com.project.models.Progress;

public interface ProgressService {

  void addProgress(ProgressForm progressForm);

  Progress getProgress(String login);
}
