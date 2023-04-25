package com.company.payroll.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class PayrollEmployee {
    private Integer prId;

    private BigDecimal basicpay;

    private BigDecimal overtimepay;

    private BigDecimal allowance;

    private BigDecimal transport;

    private BigDecimal otherdeduction;
    
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

    private BigDecimal totalpay;

    private String payperiod;

    private LocalDate paymentdate;

    private Integer eId;
}