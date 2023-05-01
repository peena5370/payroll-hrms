package com.company.payroll.filter;

import java.io.IOException;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 
 * Created 26 Apr 2023
 * TODO to be implemented
 */
public class ServletFilter extends HttpFilter {
	private static final long serialVersionUID = 2356342253571770847L;
	private Logger log = LoggerFactory.getLogger(ServletFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
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
		super.destroy();
	}
}
