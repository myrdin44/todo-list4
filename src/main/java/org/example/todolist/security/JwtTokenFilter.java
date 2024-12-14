package org.example.todolist.security;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.example.todolist.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@Component
public class JwtTokenFilter extends GenericFilterBean {
    private final JwtService jwtService;
    private final UserService userService;

    public JwtTokenFilter(final JwtService jwtService, final UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        //lay token tu header Auuthorization
        String authHeader = httpRequest.getHeader("Authorization");
        String jwtToken = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwtToken = authHeader.substring(7); //bo "Bearer " de lay token
        }

        //check null cua jwtToken va kiem tra hop le
        try {
            if (jwtToken != null && jwtService.isTokenValid(jwtToken)) {
                String username = jwtService.extractUsername(jwtToken);
                Authentication auth = jwtService.getAuthentication(jwtToken);
                if (auth != null) {
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        } catch (JwtException e) {
            logger.error("JWT validation failed: {}");
        }

        //tiep tuc chuoi filter
        chain.doFilter(request, response);
    }
}
