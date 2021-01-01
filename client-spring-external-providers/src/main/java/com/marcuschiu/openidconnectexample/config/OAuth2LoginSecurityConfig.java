package com.marcuschiu.openidconnectexample.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableWebSecurity
public class OAuth2LoginSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .authorizeRequests(ar -> ar.anyRequest().authenticated())
//                .authorizeRequests().anyRequest().authenticated() // same as above
                .authorizeRequests(authorizeRequests -> authorizeRequests
                        // Spring adds authorities to the principal based on the scopes it
                        // received from the provider, prefixed with "SCOPE_"
                        .mvcMatchers("/logged-out").permitAll()
                        .mvcMatchers("/my-endpoint").hasAuthority("SCOPE_openid")
                        .anyRequest().authenticated())

                // OAuth Login
                // oauth2Login() is used in a similar manner to httpBasic() and formLogin()
//                .oauth2Login();
                // oidcUserService(..) on OAuth login, app makes GET call to https://openidconnect.googleapis.com/v1/userinfo
//                .oauth2Login(ol -> ol.userInfoEndpoint().oidcUserService(googleUserService()))
                .oauth2Login(oauthLogin -> oauthLogin.permitAll())

                // MORE CUSTOM .oauth2Login()
                // see: https://www.baeldung.com/spring-security-5-oauth2-login

                // Logout
                .logout(logout -> logout.logoutSuccessHandler(oidcLogoutSuccessHandler()));
    }

    /**
     *
     * @param amb
     */
    @Override
    protected void configure(AuthenticationManagerBuilder amb) {

    }

    private OidcUserService googleUserService() {
        Set<String> googleScopes = new HashSet<>();
        googleScopes.add("https://www.googleapis.com/auth/userinfo.email");
        googleScopes.add("https://www.googleapis.com/auth/userinfo.profile");

        OidcUserService googleUserService = new OidcUserService();
        googleUserService.setAccessibleScopes(googleScopes);

        return googleUserService;
    }

    @Autowired
    ClientRegistrationRepository clientRegistrationRepository;

    private LogoutSuccessHandler oidcLogoutSuccessHandler() {
        OidcClientInitiatedLogoutSuccessHandler oidcLogoutSuccessHandler = new OidcClientInitiatedLogoutSuccessHandler(this.clientRegistrationRepository);
        oidcLogoutSuccessHandler.setPostLogoutRedirectUri(URI.create("http://localhost:8080/logged-out"));
        return oidcLogoutSuccessHandler;
    }
}