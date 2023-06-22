package com.company.payroll.configuration

import com.company.payroll.filter.ServletFilter
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FilterConfig {

    @Bean
    fun testFilterRegister(): FilterRegistrationBean<ServletFilter> {
        val register: FilterRegistrationBean<ServletFilter> = FilterRegistrationBean()
        register.filter = ServletFilter()
        register.addUrlPatterns("/*")
        register.setName("Servlet-Filter")
        register.order = 1

        return register
    }
}