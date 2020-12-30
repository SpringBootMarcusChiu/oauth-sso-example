## Spring Security OAuth - Dynamic Client Registration

### Relevant information:

1. `sso-authorization-server` is a Keycloak Authorization Server wrapped as a Spring Boot application
2. There are two OIDC-Connect Clients registered in the Authorization Server:
   - Client 1
     - Client Id: ssoClient-1
     - Client secret: ssoClientSecret-1
     - Redirect Uri: http://localhost:8082/login/oauth2/code/custom
   - Client 2
     - Client Id: ssoClient-2
     - Client secret: ssoClientSecret-2
     - Redirect Uri: http://localhost:8084/login/oauth2/code/custom  
3. `sso-resource-server` is a Spring Boot based RESTFul API, acting as a backend Application
4. `sso-client-app-1` and `sso-client-app-2` are two identical Spring MVC Thymeleaf App acting our front end. They are available at [http://localhost:8082/](http://localhost:8082/) and [http://localhost:8084/](http://localhost:8084/) respectively.
5. There are two users registered in the Authorization Server:
   1. john@test.com / 123
   2. mike@other.com / pass

## Relevant Articles: 
- [Simple Single Sign-On with Spring Security OAuth2](http://confluence.marcuschiu.com/x/oQVDAQ)
