package com.company.payroll.model

import lombok.AllArgsConstructor
import lombok.RequiredArgsConstructor
import java.math.BigDecimal
import java.time.LocalDate

@AllArgsConstructor
@RequiredArgsConstructor
data class StaffPromotion(
    var pId: Int,
    var currentSalary: BigDecimal,
    var promoteSalary: BigDecimal,
    var promoteDate: LocalDate,
    var comment: String,
    var title: Title,
    var staffId: Int
)
