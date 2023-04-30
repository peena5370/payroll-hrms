package com.company.payroll.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.company.payroll.interceptor.ApiInterceptor;
import com.company.payroll.utils.JwtTokenUtils;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

	private JwtTokenUtils jwtTokenUtils;
	
	public InterceptorConfig(JwtTokenUtils jwtTokenUtils) {
		this.jwtTokenUtils = jwtTokenUtils;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new ApiInterceptor(jwtTokenUtils))
		.addPathPatterns("/api/**")
		.excludePathPatterns("/login", "/logout", "/v3/api-docs/**", "/swagger-ui/**")
		.order(2);
	}
}
