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
public class Promotion {
    private Integer pId;

    private BigDecimal currentsalary;

    private BigDecimal promotesalary;

    private LocalDate promotedate;

    private Integer titleno;

    private String comment;

    private Integer eId;

    private Integer mId;
}