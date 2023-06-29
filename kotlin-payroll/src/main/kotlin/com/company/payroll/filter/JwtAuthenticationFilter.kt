package com.company.payroll.filter

import com.company.payroll.util.JwtTokenUtils
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(@Autowired private val jwtTokenUtils: JwtTokenUtils): OncePerRequestFilter() {
  override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
    // Token empty/null, pass the filter
    val header = request.getHeader("Authorization")
    if(header == null || header.isEmpty() || !header.startsWith("Bearer")) {
      filterChain.doFilter(request, response)
      return;
    }

    val token = header.substring(7)

    // Token not valid, pass the filter
    if(!jwtTokenUtils.validateToken(token)) {
      filterChain.doFilter(request, response)
    }

    // Token exist and not expired, get username
    val username = jwtTokenUtils.getUsername(token)

    // Initialize authentication token
    val authToken = UsernamePasswordAuthenticationToken(username, null, ArrayList())
    authToken.details = WebAuthenticationDetailsSource().buildDetails(request)

    // Pass authentication token to security context
    SecurityContextHolder.getContext().authentication = authToken

    filterChain.doFilter(request, response)
  }

}