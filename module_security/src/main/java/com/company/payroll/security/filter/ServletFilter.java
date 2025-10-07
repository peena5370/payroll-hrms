package com.company.payroll.security.filter;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import jakarta.servlet.http.HttpFilter;
import lombok.extern.slf4j.Slf4j;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ServletFilter extends HttpFilter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
        log.info("Servlet filter initialized at:{} ", ZonedDateTime.now());
        log.info("Servlet filter name: {} ", filterConfig.getFilterName());
        super.init(filterConfig);
	}

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		log.info("Remote address: [{}] Filter starts at: {}", request.getRemoteAddr(), LocalDateTime.now());
		super.doFilter(request, response, chain);
	}
	
	@Override
	public void destroy() {
		log.info("Servlet filter destroyed.");
	}
}
