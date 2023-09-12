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
public class AmsLoan {
    private Long id;

    private String refNum;

    private String reason;

    private BigDecimal amount;

    private LocalDate dateRepayFrom;

    private LocalDate dateRepayTo;

    private BigDecimal repayBalance;

    private LocalDate dateApply;

    private LocalDate dateApproved;

    private Byte status;

    private Long staffId;
}