package com.company.payroll.config;

import com.company.payroll.department.interceptor.DepartmentInterceptor;
import com.company.payroll.employee.interceptor.EmployeeInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebInterceptorConfig  implements WebMvcConfigurer {
    private final DepartmentInterceptor departmentInterceptor;
    private final EmployeeInterceptor employeeInterceptor;

    public WebInterceptorConfig(DepartmentInterceptor departmentInterceptor,
                                EmployeeInterceptor employeeInterceptor) {
        this.departmentInterceptor = departmentInterceptor;
        this.employeeInterceptor = employeeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(departmentInterceptor)
                .addPathPatterns("/api/department/**")
                .excludePathPatterns("/v3/api-docs/**", "/swagger-ui/**")
                .order(1);

        registry.addInterceptor(employeeInterceptor)
                .addPathPatterns("/api/employee/**")
                .excludePathPatterns("/v3/api-docs/**", "/swagger-ui/**")
                .order(2);
    }
}
