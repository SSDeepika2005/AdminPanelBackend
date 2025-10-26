package com.example.AdminBackend.authentication;


import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final String SECRET_KEY = "mysecretcodeisveryuniquelongmysecretcodeisveryverygreatandsuperlongbestkeyismykey";

    @Override
    protected void doFilterInternal(@Nonnull HttpServletRequest request,
                                    @Nonnull HttpServletResponse response,
                                    @Nonnull FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            try {
                Claims claims = Jwts.parser()
                        .setSigningKey(SECRET_KEY)
                        .parseClaimsJws(token)
                        .getBody();

                String username = claims.getSubject();
                String roles = (String) claims.get("roles"); // can be comma-separated if multiple roles

//                Collection<SimpleGrantedAuthority> authorities = Arrays.stream(roles.split(","))
//                        .map(SimpleGrantedAuthority::new)
//                        .collect(Collectors.toList());
                
                Collection<SimpleGrantedAuthority> authorities = Arrays.stream(roles.split(","))
                	    .map(role -> new SimpleGrantedAuthority("ROLE_" + role))  // Add ROLE_ prefix
                	    .collect(Collectors.toList());

                System.out.println("Authenticated user: " + username + ", authorities: " + authorities);

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        username, null, authorities);

                SecurityContextHolder.getContext().setAuthentication(auth);

            } catch (Exception e) {
                System.out.println("JWT validation failed: " + e.getMessage());
            }
        }

        filterChain.doFilter(request, response);
    }
}
