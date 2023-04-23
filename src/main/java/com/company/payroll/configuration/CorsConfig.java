package com.company.payroll.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// CORS policy configuration
@Configuration//配置类
public class CorsConfig implements WebMvcConfigurer {
		@Override
		public void addCorsMappings(CorsRegistry registry) {
			registry.addMapping("/**")//对所有请求路径
	          .allowCredentials(true)//允许cookie等凭证
			  .allowedOriginPatterns("*")//允许所有域名
			  .allowedMethods("GET", "POST", "DELETE", "PUT","PATCH")//允许所有方法
			  .maxAge(3600);  
		}
}
