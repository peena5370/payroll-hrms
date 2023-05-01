package com.company.payroll.filter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.company.payroll.utils.JwtTokenUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Created on 26 Apr 2023
 * 
 * <p> Updated at 1 May 2023. Include Spring default authorization mechanism for authenticate request made from users.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtTokenUtils jwtTokenUtils;
    
    public JwtAuthenticationFilter(JwtTokenUtils jwtTokenUtils) {
    	this.jwtTokenUtils = jwtTokenUtils;
    }
    
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String header = request.getHeader("Authorization");
		
		// Token empty/null, pass the filter
		if(header == null || header.isEmpty() || !header.startsWith("Bearer")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		final String token = header.substring(7);
		
		// Token not valid, pass the filter
		if(!jwtTokenUtils.validateToken(token)) {
			filterChain.doFilter(request, response);
		}
		
		// Token exist and not expired, get username
		String username = jwtTokenUtils.getUsername(token);

		// Initialize authentication token
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
		authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		
		// Pass authentication token to security context
		SecurityContextHolder.getContext().setAuthentication(authToken);
		
		filterChain.doFilter(request, response);
	}
}
