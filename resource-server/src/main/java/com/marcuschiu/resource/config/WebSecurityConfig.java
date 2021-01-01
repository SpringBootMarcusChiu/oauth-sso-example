package com.marcuschiu.resource.config;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

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
                // .oauth2ResourceServer().jwt(); - explicitly specify this to behave as a
                // Resource Server and that we’ll be using JWT formatted Access Tokens
                .oauth2ResourceServer().jwt();
                // other ways of specifying .oauth2ResourceServer().jwt();
//                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
//                .oauth2ResourceServer(oauth2 -> oauth2.jwt());
                // .oauth2ResourceServer().jwt() is default behavior, we can customize it:
                // see: https://www.baeldung.com/spring-security-oauth-jwt#custom-attr
                // see: resource-server-examples module

        // Anyone with an access token having the read scope can get Foos.
        // In order to POST a new Foo, their token should have a write scope
    }

    /**
     * used in tandem with OrganizationSubClaimAdapter
     * see: https://www.baeldung.com/spring-security-oauth-jwt#rs-config
     * this bean requires the following property to be set:
     * - spring.security.oauth2.resourceserverjwk-set-uri=http://localhost:8083/auth/realms/baeldung/protocol/openid-connect/certs
     * @param properties
     * @return
     */
    @Bean
    public JwtDecoder jwtDecoder(OAuth2ResourceServerProperties properties) {
        NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withJwkSetUri(properties.getJwt().getJwkSetUri()).build();
        jwtDecoder.setClaimSetConverter(new OrganizationSubClaimAdapter());
        return jwtDecoder;
    }
}