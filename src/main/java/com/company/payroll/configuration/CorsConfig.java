package com.company.payroll.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// CORS policy configuration
@Configuration
public class CorsConfig implements WebMvcConfigurer {
		@Override
		public void addCorsMappings(CorsRegistry registry) {
			registry.addMapping("/**")// Mapp all requests
	          .allowCredentials(true)// Allow cookie credentials
			  .allowedOriginPatterns("*")// Allow all origins
			  .allowedMethods("GET", "POST", "DELETE", "PUT","PATCH")// Allow all methods
			  .maxAge(3600);  
		}
}
