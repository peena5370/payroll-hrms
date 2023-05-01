package com.company.payroll.configuration;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * CORS policy configuration
 * 
 * Updated at 30 Apr 2023
 * 
 * Change deprecated WebMvsConfigurer().addCorsMappings(CorsRegistry) to CorsConfigurationSource()
 */
@Configuration
public class CorsConfig {
		
		@Bean
		CorsConfigurationSource corsConfigurationSource() {
			CorsConfiguration configuration = new CorsConfiguration();
			configuration.setAllowedOrigins(Arrays.asList("http:localhost:8080"));
			configuration.setAllowCredentials(true);
			configuration.setAllowedOriginPatterns(Arrays.asList("*"));
			configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
			configuration.setAllowedHeaders(Arrays.asList("X-Requested-With","Origin","Content-Type","Accept","Authorization"));
			configuration.setExposedHeaders(Arrays.asList("Authorization"));
			
			UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
			source.registerCorsConfiguration("/**", configuration);
			return source;
			
		}
}
