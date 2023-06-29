package com.company.payroll.configuration

import com.company.payroll.filter.JwtAuthenticationFilter
import com.company.payroll.service.impl.UserAccountServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.CorsDsl
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
class WebSecurityConfig(@Autowired private val jwtAuthenticationFilter: JwtAuthenticationFilter,
                        @Autowired private val userAccountServiceImpl: UserAccountServiceImpl) {

  @Autowired
  fun authenticationManagerBuilder(auth: AuthenticationManagerBuilder) {
    auth.userDetailsService<UserDetailsService>(userAccountServiceImpl).passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder())
  }

  @Bean
  fun getAuthenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager? {
    return authenticationConfiguration.authenticationManager
  }

  @Bean
  fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
    http {
      csrf { disable() }
      cors { CorsDsl().configurationSource = corsConfigurationSource() }
      authorizeRequests {
//        authorize("/api/users/login", permitAll)
//        authorize("/swagger-ui/**",permitAll)
//        authorize("/v3/api-docs/**", permitAll)
//        authorize(anyRequest, authenticated)
        authorize("/**", permitAll)
      }
      sessionManagement {
        sessionCreationPolicy = SessionCreationPolicy.STATELESS
      }
      httpBasic {  }
      logout {
        logoutUrl = "/api/users/logout"
        permitAll()
      }
      addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
    }

    return http.build()
  }

  @Bean
  fun corsConfigurationSource(): CorsConfigurationSource {
    val configuration = CorsConfiguration()
    configuration.allowedOrigins = listOf("http://localhost:8080", "http://localhost:8081", "http://localhost:9000")
    configuration.allowCredentials = true
    configuration.allowedOriginPatterns = listOf("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
    configuration.allowedHeaders = listOf("X-Requested-With","Origin","Content-Type","Accept","Authorization")
    configuration.exposedHeaders = listOf("Authorization", "Content-Disposition")

    val source = UrlBasedCorsConfigurationSource()
    source.registerCorsConfiguration("/**", configuration)

    return source
  }
}