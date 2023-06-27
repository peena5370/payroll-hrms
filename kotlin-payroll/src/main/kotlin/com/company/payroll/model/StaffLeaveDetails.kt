package com.company.payroll.model

import com.company.payroll.annotation.NoArg
import lombok.NoArgsConstructor

/**
 * @author caroline
 *
 * <p> Created: 22 Jun 2023
 * <p> Use as model for table staff_leave_details
 */
@NoArg
data class StaffLeaveDetails(
    var ldId: Int,
    var annualLeave: Int,
    var hospitalityLeave: Int,
    var maternityLeave: Int,
    var unpaidLeave: Int
)
