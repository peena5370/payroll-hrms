package com.company.payroll.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.company.payroll.service.impl.UserAccountService;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Autowired
	private UserAccountService userAccountService;
	
	@Autowired
	void registerProvider(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userAccountService);
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((request) ->
                // Temporary allow all requests
                request.requestMatchers("/login", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .anyRequest().authenticated())
                		.csrf(csrf -> csrf.disable())
                		.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                		.cors(withDefaults())
                		.httpBasic(withDefaults())
//			.formLogin((form) -> form.loginPage("/login")
//					.permitAll())
                		.logout((logout) -> logout.logoutUrl("/logout").permitAll());
		return http.build();
	}
	
}
