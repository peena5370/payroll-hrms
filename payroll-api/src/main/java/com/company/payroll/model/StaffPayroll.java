package com.company.payroll.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class StaffPayroll {
    private Integer prId;
    private BigDecimal basicPay;
    private BigDecimal overtimePay;
    private BigDecimal allowance;
    private BigDecimal transport;
    private BigDecimal otherDeduction;
    @JsonProperty("employee_epf")
    private BigDecimal employeeEpf;
    @JsonProperty("employee_socso")
    private BigDecimal employeeSocso;
    @JsonProperty("employee_eis")
    private BigDecimal employeeEis;
    @JsonProperty("employer_epf")
    private BigDecimal employerEpf;
    @JsonProperty("employer_socso")
    private BigDecimal employerSocso;
    @JsonProperty("employer_eis")
    private BigDecimal employerEis;
    @JsonProperty("mtd_pcb")
    private BigDecimal mtdPcb;
    private BigDecimal totalPay;
    @Schema(description="Pay period string format: 'yyyy-MM-dd to yyyy-MM-dd'")
    private String payPeriod;
    private LocalDate paymentDate;
    private Integer staffId;
//    private Integer eId;
}