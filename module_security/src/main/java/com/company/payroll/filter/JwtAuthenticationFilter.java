package com.company.payroll.filter;

import java.io.IOException;
import java.util.List;

import com.company.payroll.service.JwtUserDetailsService;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.company.payroll.util.JwtTokenUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailsService jwtUserDetailsService;

    public JwtAuthenticationFilter(JwtTokenUtil jwtTokenUtil, JwtUserDetailsService jwtUserDetailsService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.jwtUserDetailsService = jwtUserDetailsService;
    }
    
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
        log.info("JwtAuthenticationFilter started.");
        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            try {
                Claims claims = jwtTokenUtil.getClaims(token);
                String username = claims.getSubject();

                // Get roles from the JWT claims (e.g., "roles" claim)
                List<String> roles = claims.get("roles", List.class);

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    // Use the new service to build a UserDetails object from the JWT claims
                    UserDetails userDetails = jwtUserDetailsService.buildUser(username, roles);

                    // Proceed with setting the authentication in the SecurityContextHolder
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            } catch (Exception e) {
                // Log and handle invalid or expired tokens
                log.error("JWT authentication failed: {}", e.getMessage());
            }
        }

        log.info("JwtAuthenticationFilter ended.");
        filterChain.doFilter(request, response);
	}
}
