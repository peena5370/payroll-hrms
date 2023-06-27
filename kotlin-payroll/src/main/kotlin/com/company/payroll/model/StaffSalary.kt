package com.company.payroll.model

import com.company.payroll.annotation.NoArg
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import lombok.RequiredArgsConstructor
import java.math.BigDecimal
import java.time.LocalDate

@NoArg
data class StaffSalary(
    var sId: Int,
    var monthlySalary: BigDecimal,
    var annualSalary: BigDecimal,
    var dateUpdate: LocalDate?
)
