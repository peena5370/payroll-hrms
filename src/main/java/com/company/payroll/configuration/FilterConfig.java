package com.company.payroll.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.company.payroll.filter.TestFilter;

@Configuration
public class FilterConfig {
	
	@Bean
	FilterRegistrationBean<TestFilter> testFilterRegister() {
		FilterRegistrationBean<TestFilter> register = new FilterRegistrationBean<>();
		register.setFilter(new TestFilter());
		register.addUrlPatterns("/*");
		register.setName("Test-Filter");
		register.setOrder(1);
		
		return register;
	}
}
