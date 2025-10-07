package com.company.payroll.security.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.company.payroll.security.filter.ServletFilter;

@Configuration
public class FilterConfig {
	
	@Bean
	FilterRegistrationBean<ServletFilter> servletFilterFilterRegistrationBean() {
		FilterRegistrationBean<ServletFilter> register = new FilterRegistrationBean<>();
		register.setFilter(new ServletFilter());
		register.addUrlPatterns("/*");
		register.setName("Servlet-HttpFilter");
		register.setOrder(1);
		
		return register;
	}
}
