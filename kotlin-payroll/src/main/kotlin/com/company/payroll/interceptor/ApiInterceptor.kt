package com.company.payroll.interceptor

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import lombok.extern.slf4j.Slf4j
import mu.KotlinLogging
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import java.lang.Exception

class ApiInterceptor: HandlerInterceptor {
  private val log = KotlinLogging.logger {}
  override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
    log.info { "Pre handle trigger. Time: ${System.currentTimeMillis()}" }

    return super.preHandle(request, response, handler)
  }

  override fun postHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any, modelAndView: ModelAndView?) {
    super.postHandle(request, response, handler, modelAndView)
  }

  override fun afterCompletion(request: HttpServletRequest, response: HttpServletResponse, handler: Any, ex: Exception?) {
    super.afterCompletion(request, response, handler, ex)
  }
}