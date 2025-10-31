package com.company.payroll.config;

import com.company.payroll.security.filter.ServletSecurityFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SecurityFilterConfig {
    private final ServletSecurityFilter servletSecurityFilter;

    public SecurityFilterConfig(ServletSecurityFilter servletSecurityFilter) {
        this.servletSecurityFilter = servletSecurityFilter;
    }

    @Bean
    FilterRegistrationBean<ServletSecurityFilter> servletFilterFilterRegistrationBean() {
        FilterRegistrationBean<ServletSecurityFilter> register = new FilterRegistrationBean<>();
        register.setFilter(servletSecurityFilter);
        register.addUrlPatterns("/api/*");
        register.setName("Servlet-HttpFilter");
        register.setOrder(1);

        return register;
    }
}
