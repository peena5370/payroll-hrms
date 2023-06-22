package com.company.payroll.model

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties("jwt.config")
data class JwtTokenProperties(
    var key: String,
    var ttl: Long
)
