package org.example.todolist.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.example.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    private final UserRepository userRepository;
    @Value("${jwt.secret}")
    private String secretKey;

    public JwtService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*14))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Claims extractClaim(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token) {
        return extractClaim(token).getSubject();
    }

    public boolean isTokenExpired(String token) {
        try {
            return extractClaim(token).getExpiration().before(new Date());
        } catch (JwtException e) {
            throw new JwtException("Expired or invalid JWT token");
        }
    }

    private UserDetails getUserDetails(String token) {
        Claims claims = extractClaim(token);
        String username = claims.getSubject();
        return User
                .withUsername(username)
                .password("")
                .accountExpired(false)
                .credentialsExpired(false)
                .build();
    }

    public boolean isTokenValid(String token) {
        try {
            return extractUsername(token) != null && !isTokenExpired(token);
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public Authentication getAuthentication(String token) {
        Claims claims = extractClaim(token);
        String username = claims.getSubject();

        org.example.todolist.model.User user = userRepository.findByUsername(username);

        if (user != null) {
            CustomUserDetails customUserDetails = new CustomUserDetails(user);

            return new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
        }
        return null;
    }
}
