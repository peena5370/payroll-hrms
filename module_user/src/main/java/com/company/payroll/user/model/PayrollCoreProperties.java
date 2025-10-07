package com.company.payroll.user.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "payroll.core")
public class PayrollCoreProperties {

    private String endpoint;
}
