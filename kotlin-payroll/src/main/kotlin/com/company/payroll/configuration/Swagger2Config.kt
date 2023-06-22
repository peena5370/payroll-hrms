package com.company.payroll.configuration

import io.swagger.v3.oas.models.ExternalDocumentation
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Swagger2Config {

    @Bean
    fun createRestApi(): OpenAPI =
        OpenAPI()
            .info(Info().title("Spring Boot Payroll API")
                .description("HR Payroll Management System Kotlin Version")
                .version("v2.0")
                .license(License().name("Apache License, Version 2.0")
                    .url("https://www.apache.org/licenses/LICENSE-2.0")))
            .externalDocs(ExternalDocumentation().description("Currently not available"))
}