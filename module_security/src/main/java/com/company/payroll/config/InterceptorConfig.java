package com.company.payroll.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.company.payroll.interceptor.ApiInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new ApiInterceptor())
		.addPathPatterns("/api/**")
		.excludePathPatterns("/api/users/login", "/logout", "/v3/api-docs/**", "/swagger-ui/**")
		.order(2);
	}
}
