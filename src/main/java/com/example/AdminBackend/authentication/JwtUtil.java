package com.example.AdminBackend.authentication;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component 
public class JwtUtil {
private final String secret="mysecretcodeisveryuniquelongmysecretcodeisveryverygreatandsuperlongbestkeyismykey";

	    @Value("${jwt.expiration}")
	    private long expiration;

	    // Generate Key from secret
	    private java.security.Key getKey() {
	        return Keys.hmacShaKeyFor(secret.getBytes());
	    }

	    // Generate JWT token
	    public String generateToken(String email, String role) {
	        return Jwts.builder()
	                .setSubject(email)
	                .claim("roles", role) // store role in claim
	                .setExpiration(new Date(System.currentTimeMillis() + expiration))
	                .signWith(getKey(), SignatureAlgorithm.HS256)
	                .compact();
	    }

	    // Validate token
	    public boolean validateToken(String token) {
	        try {
	            Jwts.parserBuilder()
	                .setSigningKey(getKey())
	                .build()
	                .parseClaimsJws(token);
	            return true;
	        } catch (Exception e) {
	            return false;
	        }
	    }

	    // Extract role from token
	    public String extractRole(String token) {
	        return (String) Jwts.parserBuilder()
	                .setSigningKey(getKey())
	                .build()
	                .parseClaimsJws(token)
	                .getBody()
	                .get("roles"); // match the claim key
	    }

	    // Extract email/username from token
	    public String extractEmail(String token) {
	        return Jwts.parserBuilder()
	                .setSigningKey(getKey())
	                .build()
	                .parseClaimsJws(token)
	                .getBody()
	                .getSubject();
	    }
}
