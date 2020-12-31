package com.marcuschiu.resource.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
            .and()
                .authorizeRequests()
//                    .anyRequest().permitAll()
                    .antMatchers(HttpMethod.GET, "/user/info", "/api/foos/**").hasAuthority("SCOPE_read")
                    .antMatchers(HttpMethod.POST, "/api/foos").hasAuthority("SCOPE_write")
                    .anyRequest().authenticated()
            .and()
                .oauth2ResourceServer().jwt();
//                .oauth2ResourceServer(oauth2 -> oauth2.jwt());

        // Anyone with an access token having the read scope can get Foos.
        // In order to POST a new Foo, their token should have a write scope
    }
}