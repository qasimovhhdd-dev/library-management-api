package com.yourname.library.user;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;


public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtAuthFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        System.out.println("========== JWT FILTER ==========");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            System.out.println("Authorization header yoxdur və ya yanlışdır.");
            filterChain.doFilter(request, response);
            return;
        }

        System.out.println("Authorization: " + authHeader);

        String token = authHeader.substring(7);

        boolean valid = jwtService.isTokenValid(token);
        System.out.println("Token valid: " + valid);

        if (valid) {
            String username = jwtService.extractUsername(token);
            String role = jwtService.extractRole(token);

            System.out.println("Username: " + username);
            System.out.println("Role: " + role);

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            username,
                            null,
                            List.of(new SimpleGrantedAuthority("ROLE_" + role))
                    );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            System.out.println("Authentication: "
                    + SecurityContextHolder.getContext().getAuthentication());
        } else {
            System.out.println("Token etibarsızdır!");
        }

        System.out.println("========== FILTER END ==========");

        filterChain.doFilter(request, response);
    }
}