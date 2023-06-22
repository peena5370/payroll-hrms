package com.company.payroll.model

import lombok.AllArgsConstructor
import lombok.RequiredArgsConstructor
import java.math.BigDecimal
import java.time.LocalDate

@AllArgsConstructor
@RequiredArgsConstructor
data class Salary(
    var sId: Int,
    var monthlySalary: BigDecimal,
    var annualSalary: BigDecimal,
    var dateUpdate: LocalDate
)
