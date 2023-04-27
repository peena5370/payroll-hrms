package com.company.payroll.configuration;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * CORS policy configuration
 */
@Configuration
public class CorsConfig {
//public class CorsConfig implements WebMvcConfigurer {
//		@Override
//		public void addCorsMappings(CorsRegistry registry) {
//			registry.addMapping("/**")// mapping all paths
//			  .allowedOrigins("http://localhost:8080")
//	          .allowCredentials(true)// Allow cookie credentials
//			  .allowedOriginPatterns("*")// Allow all origins
//			  .allowedMethods("GET", "POST", "DELETE", "PUT")
//			  .maxAge(3600);  
//		}
		
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
