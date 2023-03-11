package com.project.security.filters;

import com.google.gson.Gson;
import com.project.models.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ExceptionFilter extends OncePerRequestFilter {
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    try {
      filterChain.doFilter(request, response);
    } catch (IOException | IllegalArgumentException exception) {
      response.setStatus(HttpStatus.BAD_REQUEST.value());
      Gson gson = new Gson();
      response.getWriter().write(
          gson.toJson(ExceptionResponse.builder()
              .status(HttpStatus.BAD_REQUEST)
              .message(exception.getMessage()).build())
      );
    }
  }
}
