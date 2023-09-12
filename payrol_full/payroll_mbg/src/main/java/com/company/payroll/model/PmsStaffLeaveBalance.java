package com.company.payroll.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class PmsStaffLeaveBalance {
    private Long id;

    private Integer annualLeave;

    private Integer hospitalityLeave;

    private Integer maternityLeave;

    private Integer unpaidLeave;

    private Long staffId;
}