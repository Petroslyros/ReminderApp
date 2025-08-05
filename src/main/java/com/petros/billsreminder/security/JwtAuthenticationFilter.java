package com.petros.billsreminder.security;

import com.petros.billsreminder.security.authentication.JWTService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;

        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7).trim();
        try {
            username = jwtService.extractSubject(jwt);

            if(username!= null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                if(!jwtService.isTokenValid(jwt, userDetails)) {
                    throw new BadCredentialsException("Invalid Token");
                }
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        } catch (ExpiredJwtException e) {
            //triggers the AuthenticationEntryPoint 401
            throw new AuthenticationCredentialsNotFoundException("Expired token", e);
        } catch (JwtException | IllegalArgumentException e) {
            //triggers the AuthenticationEntryPoint 401
            throw new BadCredentialsException("Invald token");
        } catch (Exception e) {
            // triggers the AccessDeniedException 403
            throw new AccessDeniedException("Token validation failed");
        }

        filterChain.doFilter(request, response);
    }
}
