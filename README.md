## Spring Security OAuth - Dynamic Client Registration

### Relevant information:

1. `auth-server` is a Keycloak Authorization Server wrapped as a Spring Boot application. There are 3 OIDC-Connect Clients registered in the Authorization Server:
   - Client 1
     - Client Id: ssoClient-1
     - Client secret: ssoClientSecret-1
     - Redirect Uri: http://localhost:8082/login/oauth2/code/custom
   - Client 2
     - Client Id: ssoClient-2
     - Client secret: ssoClientSecret-2
     - Redirect Uri: http://localhost:8084/login/oauth2/code/custom
   - Client 3
     - Client Id: client-angular
     - Client secret: client-angular-secret
     - Redirect Uri: http://localhost:8084/
3. `resource-server` is a Spring Boot based RESTFul API, acting as a backend Application
4. `client-spring-1` and `client-spring-2` are two identical Spring MVC Thymeleaf App acting our front end. They are available at [http://localhost:8082/](http://localhost:8082/) and [http://localhost:8084/](http://localhost:8084/) respectively.
4. `client-spring-external-provider` similar to the 2 above but configured with external OIDC providers (Google)
4. `client-angular` is an Angular Client, available at http://localhost:8084/
5. There are two users registered in the Authorization Server:
   1. john@test.com / 123
   2. mike@other.com / pass
    
### Main Articles:
1. [Simple Single Sign-On with Spring Security OAuth2](http://confluence.marcuschiu.com/x/oQVDAQ)
2. [OAuth2 (Auth Server + Resource Server + Angular Client)](https://www.baeldung.com/rest-api-spring-oauth2-angular)

### Other Articles:
- [OAuth2 for a Spring REST API – Handle the Refresh Token in Angular](https://www.baeldung.com/spring-security-oauth2-refresh-token-angular)
- [Logout in an OAuth Secured Application](http://www.baeldung.com/logout-spring-security-oauth)
- [Using JWT with Spring Security OAuth](https://www.baeldung.com/spring-security-oauth-jwt)
- [Customizing Themes for Keycloak](https://www.baeldung.com/spring-keycloak-custom-themes)
- [Customizing the Login Page for Keycloak](https://www.baeldung.com/keycloak-custom-login-page)
- [Custom User Attributes with Keycloak](https://www.baeldung.com/keycloak-custom-user-attributes)
- [Keycloak User Self-Registration](https://www.baeldung.com/keycloak-user-registration)