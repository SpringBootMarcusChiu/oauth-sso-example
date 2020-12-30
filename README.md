## Spring Security OAuth - Dynamic Client Registration

### Relevant information:

1. `sso-authorization-server` is a Keycloak Authorization Server wrapped as a Spring Boot application
2. There are two OIDC-Connect Clients registered in the Authorization Server:
First -
   1. Client Id: ssoClient-1
   2. Client secret: ssoClientSecret-1
   3. Redirect Uri: http://localhost:8082/ui-one/login/oauth2/code/custom
And second -
   1. Client Id: ssoClient-2
   2. Client secret: ssoClientSecret-2
   3. Redirect Uri: http://localhost:8084/ui-two/login/oauth2/code/custom  
3. `sso-resource-server` is a Spring Boot based RESTFul API, acting as a backend Application
4. `sso-client-app-1` and `sso-client-app-2` are two identical Spring MVC Thymeleaf App acting our front end. They are available at [http://localhost:8082/ui-one/](http://localhost:8082/ui-one) and [http://localhost:8084/ui-two/](http://localhost:8084/ui-two/) respectively.
5. There are two users registered in the Authorization Server:
   1. john@test.com / 123
   2. mike@other.com / pass
6. The module uses the new OAuth stack with Java 13.   

## Relevant Articles: 
- [Simple Single Sign-On with Spring Security OAuth2](https://www.baeldung.com/sso-spring-security-oauth2)
