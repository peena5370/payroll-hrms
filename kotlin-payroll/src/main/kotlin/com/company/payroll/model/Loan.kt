package com.company.payroll.model

import lombok.AllArgsConstructor
import lombok.RequiredArgsConstructor
import java.math.BigDecimal
import java.time.LocalDate

@AllArgsConstructor
@RequiredArgsConstructor
data class Loan(
    var loanId: Int,
    var refNum: String,
    var reason: String,
    var loanAmount: BigDecimal,
    var applicationDate: LocalDate,
    var loanStatus: UByte,
    var approvedDate: LocalDate,
    var staffId: Int
)
