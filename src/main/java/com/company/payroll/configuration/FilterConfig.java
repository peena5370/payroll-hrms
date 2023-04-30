package com.company.payroll.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.company.payroll.filter.JwtAuthenticationFilter;
import com.company.payroll.filter.ServletFilter;
import com.company.payroll.service.impl.UserAccountService;
import com.company.payroll.utils.JwtTokenUtils;

@Configuration
public class FilterConfig {
	
	private JwtTokenUtils jwtTokenUtils;
	
	private UserAccountService userAccountService;
	
	public FilterConfig(JwtTokenUtils jwtTokenUtils, UserAccountService userAccountService) {
		this.jwtTokenUtils = jwtTokenUtils;
		this.userAccountService = userAccountService;
	}
	
	@Bean
	FilterRegistrationBean<ServletFilter> testFilterRegister() {
		FilterRegistrationBean<ServletFilter> register = new FilterRegistrationBean<>();
		register.setFilter(new ServletFilter());
		register.addUrlPatterns("/*");
		register.setName("Servlet-Filter");
		register.setOrder(1);
		
		return register;
	}
	
	@Bean
	FilterRegistrationBean<JwtAuthenticationFilter> authFilterRegister() {
		FilterRegistrationBean<JwtAuthenticationFilter> register = new FilterRegistrationBean<>();
		register.setFilter(new JwtAuthenticationFilter(jwtTokenUtils, userAccountService));
		register.addUrlPatterns("/api/*");
		register.setName("Auth-Filter");
		register.setOrder(2);
		
		return register;
	}
}
