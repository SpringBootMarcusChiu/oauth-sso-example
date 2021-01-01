package com.marcuschiu.openidconnectexample.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class DefaultController {

    @GetMapping("logged-out")
    public String loggedOut() {
        return "LOGGED OUT";
    }

    @GetMapping("/")
    public Principal home(Principal principal) {
        return principal;
    }

    @GetMapping("/oidc-principal-1")
    public OidcUser getOidcUserPrincipal(@AuthenticationPrincipal OidcUser principal) {
        return principal;
    }

    @GetMapping("/oidc-principal-2")
    public OidcUser getOidcPrincipal() {
        OidcUser principal = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof OidcUser) {
            principal = ((OidcUser) authentication.getPrincipal());
        }

        return principal;
    }
}
