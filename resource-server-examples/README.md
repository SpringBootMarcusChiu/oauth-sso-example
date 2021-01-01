## Spring Security OAuth Resource Servers

### Relevant information:

1. `authorization-server` is a Keycloak Authorization Server wrapped as a Spring Boot application
2. There are two OAuth Client registered in the Authorization Server:
   1. Client Id: fooClient, barClient
   2. Client secret: fooClientSecret, barClientSecret
   3. Redirect Uri: http://localhost:8080/
3. There are two users registered in the Authorization Server:
   1. john@test.com / 123
   2. mike@other.com / pass
4. `resource-server-jwt` is a Spring Boot Resource Server which exchanges JWT OAuth Tokens with the above authorization-server and dishes out /foos/**
5. `resource-server-opaque` is a Spring Boot Resource Server which exchanges Opaque OAuth Tokens with the above authorization-server and dishes out /bars/**
    
### Relevant Articles:

- [OAuth 2.0 Resource Server With Spring Security 5](https://www.baeldung.com/spring-security-oauth-resource-server)
