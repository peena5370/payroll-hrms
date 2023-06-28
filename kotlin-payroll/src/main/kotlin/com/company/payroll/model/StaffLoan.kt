package com.company.payroll.model

import com.company.payroll.annotation.NoArg
import lombok.AllArgsConstructor
import lombok.RequiredArgsConstructor
import java.math.BigDecimal
import java.time.LocalDate

@NoArg
data class StaffLoan(
    var loanId: Int,
    var refNum: String,
    var reason: String,
    var loanAmount: BigDecimal,
    var applicationDate: LocalDate,
    var loanStatus: UByte,
    var approvedDate: LocalDate?,
    var staffId: Int
)
