package com.company.payroll.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class PayrollManager {
    private Integer prMgrId;

    private BigDecimal basicpay;

    private BigDecimal overtimepay;

    private BigDecimal allowance;

    private BigDecimal transport;

    private BigDecimal otherdeduction;

    private BigDecimal managerEpf;

    private BigDecimal managerSocso;

    private BigDecimal managerEis;

    private BigDecimal employerEpf;

    private BigDecimal employerSocso;

    private BigDecimal employerEis;

    private BigDecimal mtdPcb;

    private BigDecimal totalpay;

    private String payperiod;

    private LocalDate paymentdate;

    private Integer mId;
}