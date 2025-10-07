package com.company.payroll.util.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "util.snowflake")
public class SnowFlakeUtilProperties {

    private Long workerId;
    private Long datacenterId;
}
