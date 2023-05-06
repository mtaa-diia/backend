package com.doklad.api.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class JWTUtil {

    @Value("${jwt.secret}")
    private String key;


    public String generateToken(String username) {
        Date expirationDate = Date.from(ZonedDateTime.now().plusDays(2).toInstant());

        return JWT.create()
                .withSubject("User details")
                .withClaim("username", username)
                .withIssuedAt(new Date())
                .withIssuer("dija")
                .withExpiresAt(expirationDate)
                .sign(Algorithm.HMAC256(key));
    }


    public String validateToken(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(key))
                .withSubject("User details")
                .withIssuer("dija")
                .build();

        DecodedJWT decodedJWT = verifier.verify(token);

        return decodedJWT.getClaim("username").asString();
    }
}
