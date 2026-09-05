package dev.trajano.gym.core.security;

import dev.trajano.gym.core.exception.InvalidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class TokenProvider {
    @Value("${jwt.key}")
    private String key;

    @Value("${jwt.expiration}")
    private Long expirationAt;

    public String generateToken(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return buildToken(userDetails.getUsername());
    }

    private String buildToken(String username) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + expirationAt);
        return Jwts.builder().subject(username).issuedAt(now).expiration(expirationDate).signWith(getSigningKey()).compact();
    }


    public boolean isValid(String token) {
        try {
            extractClaims(token);
            return true;
        } catch (InvalidTokenException e) {
            return false;
        }
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    private Claims extractClaims(String token) {
        try {
            return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload();

        } catch (JwtException | IllegalArgumentException e) {
            throw new InvalidTokenException("Token invalid or expired");
        }
    }

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(key.getBytes());
    }
}
