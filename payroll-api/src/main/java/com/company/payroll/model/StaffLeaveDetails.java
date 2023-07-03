package com.company.payroll.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class StaffLeaveDetails {
    private Integer ldId;
    private Integer annualLeave;
    private Integer hospitalityLeave;
    private Integer maternityLeave;
    private Integer unpaidLeave;
}
