package com.project.security.filters;

import com.project.security.token.TokenAuthentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class TokenAuthenticationFilter implements Filter {

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest)servletRequest;

    String token = request.getHeader("authorization");
    TokenAuthentication tokenAuthentication = TokenAuthentication.builder().token(token).build();

    if (token == null) {
      SecurityContextHolder.clearContext();
    } else {
      SecurityContextHolder.getContext().setAuthentication(tokenAuthentication);
    }

    filterChain.doFilter(servletRequest, servletResponse);
  }
}
