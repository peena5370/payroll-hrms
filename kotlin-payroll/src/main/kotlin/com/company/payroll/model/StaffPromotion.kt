package com.company.payroll.model

import com.company.payroll.annotation.NoArg
import java.math.BigDecimal
import java.time.LocalDate

@NoArg
data class StaffPromotion(
    var pId: Int,
    var currentSalary: BigDecimal,
    var promoteSalary: BigDecimal,
    var promoteDate: LocalDate,
    var comment: String,
    var title: Title?,
    var staffDetails: StaffDetails?,
    var titleNo: Int,
    var staffId: Int
)
