package com.company.payroll.model

/**
 * @author caroline
 *
 * <p> Created: 22 Jun 2023
 * <p> Use as model for table staff_leave_details
 */
data class StaffLeaveDetails(
    var ldId: Int,
    var annualLeave: Int,
    var hospitalityLeave: Int,
    var maternityLeave: Int,
    var unpaidLeave: Int
)
