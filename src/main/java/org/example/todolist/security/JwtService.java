package org.example.todolist.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.example.todolist.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
    private String secretKey = "2852012passwordLaMatkhauMatkhauLaPasswordVanChuaDu256bitaaaaaaa";

    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("role", user.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1800))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Claims extractClaim(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token) {
        return extractClaim(token).getSubject();
    }

    public boolean isTokenExpired(String token) {
        return extractClaim(token).getExpiration().before(new Date());
    }

    public boolean isTokenValid(String token, User user) {
        return (user.getUsername().equals(extractUsername(token)) && !isTokenExpired(token));
    }
}
