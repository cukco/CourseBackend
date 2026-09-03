package com.example.Course.Security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtils {
    private final String SECRET_STRING = "3a7a3b55-252d-48b1-86f8-af50a4df5751";
    private final SecretKey key = Keys.hmacShaKeyFor(SECRET_STRING.getBytes());

    private final long EXPIRATION_TIME = 864_000_000;

    //token từ email
    public String generateToken(String email) {
        return Jwts.builder().subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    //email từ token
    public String getEmailfromtoken(String token) {
        return Jwts.parser()
                .verifyWith(key).
                build().
                parseSignedClaims(token).
                getPayload().
                getSubject();
    }

    public boolean isTokenValid(String token) {
        try{
            Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
            return true;
        }catch(Exception e){
            System.out.println("Invalid token/Outdated token");
            return false;
        }
    }
}
