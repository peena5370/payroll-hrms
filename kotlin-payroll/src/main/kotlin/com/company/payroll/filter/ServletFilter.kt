package com.company.payroll.filter

import jakarta.servlet.FilterChain
import jakarta.servlet.FilterConfig
import jakarta.servlet.http.HttpFilter
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import mu.KotlinLogging
import java.time.LocalDateTime

class ServletFilter: HttpFilter() {
    private val log = KotlinLogging.logger {}
    override fun init(filterConfig: FilterConfig?) {
        super.init(filterConfig)
    }

    override fun doFilter(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        log.info { "Remote address: [${request.remoteAddr}] Filter starts at: ${LocalDateTime.now()}" }
        super.doFilter(request, response, chain)
    }

    override fun destroy() {
        super.destroy()
    }

}