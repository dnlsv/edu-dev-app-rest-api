package com.project.exceptions;

import com.project.models.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
public class ControllerAdvice {

  @ExceptionHandler({IllegalArgumentException.class})
  public ResponseEntity<ExceptionResponse> illegalArgumentException(IllegalArgumentException illegalArgumentException) {
    return new ResponseEntity<>(
            ExceptionResponse.builder().status(HttpStatus.BAD_REQUEST).message(illegalArgumentException.getMessage()).build(),
            HttpStatus.BAD_REQUEST
    );
  }

  @ExceptionHandler({IOException.class})
  public ResponseEntity<ExceptionResponse> ioException(IOException ioException) {
    return new ResponseEntity<>(
            ExceptionResponse.builder().status(HttpStatus.BAD_REQUEST).message(ioException.getMessage()).build(),
            HttpStatus.BAD_REQUEST
    );
  }
}
