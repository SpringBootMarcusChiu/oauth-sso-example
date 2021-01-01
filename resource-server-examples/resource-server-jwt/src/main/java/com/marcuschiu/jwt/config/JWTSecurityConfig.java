package com.marcuschiu.jwt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@Configuration
public class JWTSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
          .authorizeRequests(authz -> authz
              .antMatchers(HttpMethod.GET, "/foos/**").hasAuthority("SCOPE_read")
              .antMatchers(HttpMethod.POST, "/foos").hasAuthority("SCOPE_write")
              .anyRequest().authenticated())
          .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
	}
}