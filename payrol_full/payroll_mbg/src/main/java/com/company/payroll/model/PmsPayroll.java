package com.company.payroll.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class PmsPayroll {
    private Long id;

    private BigDecimal basicPay;

    private BigDecimal overtimePay;

    private BigDecimal allowance;

    private BigDecimal transport;

    private BigDecimal loanDeduction;

    private BigDecimal otherDeduction;

    private BigDecimal employeeEpf;

    private BigDecimal employeeSocso;

    private BigDecimal employeeEis;

    private BigDecimal employerEpf;

    private BigDecimal employerSocso;

    private BigDecimal employerEis;

    private BigDecimal mtdPcb;

    private BigDecimal total;

    private String payPeriod;

    private LocalDate dateIssue;

    private Long staffId;
}