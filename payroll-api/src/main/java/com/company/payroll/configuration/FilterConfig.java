package com.company.payroll.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.company.payroll.filter.ServletFilter;

@Configuration
public class FilterConfig {
	
	@Bean
	FilterRegistrationBean<ServletFilter> testFilterRegister() {
		FilterRegistrationBean<ServletFilter> register = new FilterRegistrationBean<>();
		register.setFilter(new ServletFilter());
		register.addUrlPatterns("/*");
		register.setName("Servlet-Filter");
		register.setOrder(1);
		
		return register;
	}
}
