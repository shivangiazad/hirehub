package com.hirehub.hirehub.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        System.out.println("AUTH HEADER: " + authHeader);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            System.out.println("TOKEN FOUND: " + token.substring(0, 20));

            if (jwtUtil.validateToken(token)) {
                String email = jwtUtil.extractEmail(token);
                System.out.println("EMAIL FROM TOKEN: " + email);

                UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(
                        email, null,
                        List.of(new SimpleGrantedAuthority("ROLE_USER"))
                    );
                SecurityContextHolder.getContext().setAuthentication(auth);
            } else {
                System.out.println("TOKEN INVALID!");
            }
        } else {
            System.out.println("NO BEARER TOKEN FOUND!");
        }
        filterChain.doFilter(request, response);
    }
}
