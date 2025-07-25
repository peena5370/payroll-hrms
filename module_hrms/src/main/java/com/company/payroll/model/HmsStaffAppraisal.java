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
public class HmsStaffAppraisal {
    private Long id;

    private BigDecimal currentSalary;

    private BigDecimal promoteSalary;

    private LocalDate promoteDate;

    private String comment;

    private Byte status;

    private Long titleId;

    private Long staffId;
}