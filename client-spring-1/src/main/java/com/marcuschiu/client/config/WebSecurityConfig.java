package com.marcuschiu.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
		http
                .authorizeRequests()
                    .antMatchers("/", "/login**").permitAll()
                    .anyRequest().authenticated()
            .and()
                // oauth2Login() enables Spring Security’s OAuth 2.0 Login support
                // Since we're using Keycloak, which is by default a single sign-on
                // solution for web apps and RESTful web services, we do not need to
                // add any further configuration for SSO
				.oauth2Login();
	}

    @Bean
    WebClient webClient(ClientRegistrationRepository crr, OAuth2AuthorizedClientRepository acr) {
        ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2 = new ServletOAuth2AuthorizedClientExchangeFilterFunction(crr, acr);
        oauth2.setDefaultOAuth2AuthorizedClient(true);
        return WebClient.builder()
            .apply(oauth2.oauth2Configuration())
            .build();
    }
}
