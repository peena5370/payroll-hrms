package com.company.payroll.interceptor;

import com.company.payroll.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class DepartmentInterceptor implements HandlerInterceptor {

    private final JwtTokenUtil jwtTokenUtil;

    public DepartmentInterceptor(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info("interceptor preHandle executed");
        // check rights here, return true if allow access the api, return false if the user not allow to access the api
        String authorizationHeader = request.getHeader("Authorization");

        if(authorizationHeader != null) {
            String token = authorizationHeader.substring(7);
            Claims claims = jwtTokenUtil.getClaims(token);

            if (claims != null) {
                String userId = claims.get("userId", String.class);

                // @TODO temporary use for validate userId, will implement rights checking after this
                if("user-admin123".equals(userId)) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // log message after the controller has been accessed or before view rendered
        log.info("interceptor postHandle executed");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // log message after all the request has been completely made
        log.info("interceptor afterCompletion executed");
    }
}
