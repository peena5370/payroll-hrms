package com.company.payroll.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
//@EnableWebSecurity
public class WebSecurityConfig {
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((request) -> 
			request.requestMatchers("/**").permitAll()
				
			);
		
		return http.build();
		
	}
	
//	@Bean
//	public UserDetailsService userDetailsService() {
//		UserDetails user = User.withUsername("administrator")
//							.password("{bcrypt}$2a$10$LyyWPUq/G7ZnSj8rWuzr9uyplAglRCazGzPkRQd8tn3WgJ5lcfvze")
//							.roles("USER")
//							.build();
//		
//		return new InMemoryUserDetailsManager(user);
		
//	}
}
