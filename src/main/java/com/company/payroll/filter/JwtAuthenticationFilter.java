package com.company.payroll.filter;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import com.company.payroll.service.impl.UserAccountService;
import com.company.payroll.utils.JwtTokenUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Created on 26 Apr 2023
 * TODO to be implemented
 */
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private UserAccountService userAccountService;

    private JwtTokenUtils jwtTokenUtils;
    
    public JwtAuthenticationFilter(JwtTokenUtils jwtTokenUtils, UserAccountService userAccountService) {
    	this.jwtTokenUtils = jwtTokenUtils;
    	this.userAccountService = userAccountService;
    }
    
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		log.info("Authentication filter starts at: {}", LocalDateTime.now());
		String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String authToken = header.substring(7);
            String username = jwtTokenUtils.getUserNameFromToken(authToken);
            if (username != null) {
                UserDetails userDetails = userAccountService.loadUserByUsername(username);
                if (jwtTokenUtils.validateToken(authToken) && userDetails != null) {
                    filterChain.doFilter(request, response);
                }
            }
        }
	}
}
