package com.project.security.config;

import com.project.security.filters.TokenAuthenticationFilter;
import com.project.security.provider.TokenAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@ComponentScan("com.project")
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private TokenAuthenticationFilter tokenAuthenticationFilter;

  @Autowired
  private TokenAuthenticationProvider tokenAuthenticationProvider;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .addFilterBefore(tokenAuthenticationFilter, BasicAuthenticationFilter.class)
            .authenticationProvider(tokenAuthenticationProvider)
            .authorizeRequests()
            .antMatchers("/login", "/registration").permitAll()
            .anyRequest().authenticated();
    http.cors().and().csrf().disable();
  }
}
