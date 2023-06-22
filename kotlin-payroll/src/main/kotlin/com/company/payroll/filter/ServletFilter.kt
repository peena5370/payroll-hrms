package com.company.payroll.filter

import jakarta.servlet.FilterChain
import jakarta.servlet.FilterConfig
import jakarta.servlet.http.HttpFilter
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import java.time.LocalDateTime

class ServletFilter: HttpFilter() {
    private val log = LoggerFactory.getLogger(this.javaClass)
    override fun init(filterConfig: FilterConfig?) {
        super.init(filterConfig)
    }

    override fun doFilter(request: HttpServletRequest?, response: HttpServletResponse?, chain: FilterChain?) {
        if (request != null) {
            log.info("Remote address: [{}] Filter starts at: {}", request.remoteAddr, LocalDateTime.now())
        }
        super.doFilter(request, response, chain)
    }

    override fun destroy() {
        super.destroy()
    }

}