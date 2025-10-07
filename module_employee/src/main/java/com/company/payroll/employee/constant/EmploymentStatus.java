package com.company.payroll.employee.constant;

public enum EmploymentStatus {
    // 1. ACTIVE STATUSES
    ACTIVE,         // Standard, fully confirmed employee
    PROBATION,      // Employee in the initial evaluation period
    LOA,            // Leave of Absence (Paid or Unpaid)
    SUSPENDED,      // Temporarily suspended for disciplinary reasons
    INTERN,         // Temporary student status

    // 2. TERMINATION/INACTIVE STATUSES
    RESIGNED,       // Voluntary termination
    TERMINATED,     // Involuntary termination (e.g., for cause)
    LAID_OFF,       // Involuntary termination (e.g., budget cuts, no fault of employee)
    RETIRED,        // Voluntary separation upon retirement
    PENDING_EXIT    // In progress resignation
}
