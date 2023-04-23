package com.company.payroll.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Salary {
    private Integer sId;

    private BigDecimal monthlysalary;

    private BigDecimal annualsalary;

    private LocalDate dateupdate;

}