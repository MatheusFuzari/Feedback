package com.bosch.feedforward.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bosch.feedforward.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    private String securityKey = "kx1023109c1m28v83ur3fui7854ucthjfm";

    public String generateToken(UserEntity user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(securityKey);

            String token = JWT.create()
                    .withIssuer("auth-feedforward")
                    .withSubject(user.getEdv())
                    .withExpiresAt(this.generateExpirationDate())
                    .sign(algorithm);

            return token;

        }catch (JWTCreationException exception){
            throw new RuntimeException("Error while authenticating...");
        }
    }
    public String validateToken(String token) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(securityKey);

            return JWT.require(algorithm)
                    .withIssuer("auth-feedforward")
                    .build()
                    .verify(token)
                    .getSubject();

        }catch (JWTVerificationException exception) {
            return null;
        }
    }

    public Instant generateExpirationDate() { return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-3")); }

}
