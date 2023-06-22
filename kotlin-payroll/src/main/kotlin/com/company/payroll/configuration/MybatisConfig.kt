package com.company.payroll.configuration

import org.mybatis.spring.annotation.MapperScan
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableTransactionManagement
@MapperScan("com.company.payroll.mapper")
class MybatisConfig {
}