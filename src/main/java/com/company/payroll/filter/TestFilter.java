package com.company.payroll.filter;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

/**
 * 
 * @author leeshengxian
 * Created 26 Apr 2023
 * TODO to be implemented
 */
public class TestFilter implements Filter {
	private Logger log = LoggerFactory.getLogger(TestFilter.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		Filter.super.init(filterConfig);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.info("Filter starts at: {}", System.currentTimeMillis());
		chain.doFilter(request, response);
		log.info("Filter ends at: {}", System.currentTimeMillis());
	}
	
	@Override
	public void destroy() {
		Filter.super.destroy();
	}
}
