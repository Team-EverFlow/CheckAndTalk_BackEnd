package com.example.oauth.config;

import com.example.oauth.service.PrincipalOauthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

  private final PrincipalOauthUserService principalOauthUserService;

  @Autowired
  public WebSecurityConfig(PrincipalOauthUserService principalOauthUserService) {
    this.principalOauthUserService = principalOauthUserService;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests((req) -> req.requestMatchers("/", "/login", "/loginform").permitAll())
            .oauth2Login((oauth) -> oauth.loginPage("/login").defaultSuccessUrl("/").userInfoEndpoint((end) -> end.userService(principalOauthUserService)))
            .logout((logout) -> logout.permitAll());
    return http.build();
  }
}
