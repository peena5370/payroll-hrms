package com.company.payroll.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.company.payroll.utils.JwtTokenUtils;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtInterceptor implements HandlerInterceptor {
	
	private JwtTokenUtils jwtTokenUtils;
	
	public JwtInterceptor(JwtTokenUtils jwtTokenUtils) {
		this.jwtTokenUtils = jwtTokenUtils;
	}

	/**
	 * Created on 28 Apr 2023
	 * TODO to be updated
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		boolean bool = true;
		log.info("Pre handle trigger. Time: {}", System.currentTimeMillis());
//		final String authHeader = request.getHeader("Authorization");
//		if(authHeader!=null && authHeader.startsWith("Bearer ")) {
//			final String token = authHeader.substring(7);
//			
//			Claims claims = jwtTokenUtils.parseJwt(token);
//			if(claims != null) {
//				if("role_admin".equals(claims.get("roles"))) {
//					request.setAttribute("ROLE_ADMIN", claims);
//				}
//				if("role_manager".equals(claims.get("roles"))) {
//					request.setAttribute("ROLE_MANAGER", claims);
//				}
//				if("role_user".equals(claims.get("roles"))) {
//					request.setAttribute("ROLE_USER", claims);
//				}
//			}
//		}  else {
//			bool = false;
//			log.info("Unauthorize token. Token string: {}", authHeader);
//		}
		
		return bool;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		log.info("Post handle. Time: {}", System.currentTimeMillis());
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		log.info("After completion. Time: {}", System.currentTimeMillis());
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

}
