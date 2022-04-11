package com.project.transfer;

import com.project.models.Progress;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProgressDto {

  private int level;

  private int stars;

  private int completedTasks;

  public static ProgressDto from(Progress progress) {
    return new ProgressDto(progress.getLevel(), progress.getStars(), progress.getCompletedTasks());
  }
}
