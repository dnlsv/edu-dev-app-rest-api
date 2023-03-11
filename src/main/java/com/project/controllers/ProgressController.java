package com.project.controllers;

import com.project.forms.ProgressForm;
import com.project.services.ProgressService;
import com.project.transfer.ProgressDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProgressController {

  @Autowired
  private ProgressService progressService;

  @PostMapping("/add-progress")
  public ResponseEntity<Object> addProgress(@RequestBody ProgressForm progressForm) {
    progressService.addProgress(progressForm);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/get-progress")
  public ResponseEntity<ProgressDto> getProgress(@RequestParam String login) {
    return ResponseEntity.ok(ProgressDto.from(progressService.getProgress(login)));
  }
}
