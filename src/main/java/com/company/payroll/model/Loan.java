package com.company.payroll.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Loan {
    private Integer loanId;

    private String refnum;

    private String reason;

    private BigDecimal loanamount;

    private LocalDate applicationdate;

    private Byte loanstatus;

    private LocalDate dateapproved;

    private Integer eId;

    private Integer mId;
}