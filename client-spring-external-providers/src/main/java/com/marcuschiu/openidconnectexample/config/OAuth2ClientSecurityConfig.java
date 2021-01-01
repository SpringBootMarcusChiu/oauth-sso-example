package com.marcuschiu.openidconnectexample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class OAuth2ClientSecurityConfig {

    /**
     * for more on configuring WebClient with OAuth
     * see: https://www.baeldung.com/spring-webclient-oauth2
     * @param crr
     * @param acr
     * @return
     */
    @Bean
    public WebClient webClient(ClientRegistrationRepository crr, OAuth2AuthorizedClientRepository acr) {
        ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2 = new ServletOAuth2AuthorizedClientExchangeFilterFunction(crr, acr);
        oauth2.setDefaultOAuth2AuthorizedClient(true);
        return WebClient.builder()
                .apply(oauth2.oauth2Configuration())
                .build();
    }
}
