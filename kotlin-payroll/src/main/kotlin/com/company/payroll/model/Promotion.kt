package com.company.payroll.model

import lombok.AllArgsConstructor
import lombok.RequiredArgsConstructor
import java.math.BigDecimal
import java.time.LocalDate

@AllArgsConstructor
@RequiredArgsConstructor
data class Promotion(
    var pId: Int,
    var currentSalary: BigDecimal,
    var promoteSalary: BigDecimal,
    var promoteDate: LocalDate,
    var title: Title,
    var comment: String,
    var staffId: Int
)
