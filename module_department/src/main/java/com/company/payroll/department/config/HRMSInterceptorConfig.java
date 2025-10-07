package com.company.payroll.department.config;

import com.company.payroll.department.interceptor.DepartmentInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class HRMSInterceptorConfig implements WebMvcConfigurer {

    private final DepartmentInterceptor departmentInterceptor;

    public HRMSInterceptorConfig(DepartmentInterceptor departmentInterceptor) {
        this.departmentInterceptor = departmentInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(departmentInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/v3/api-docs/**", "/swagger-ui/**");
    }
}
