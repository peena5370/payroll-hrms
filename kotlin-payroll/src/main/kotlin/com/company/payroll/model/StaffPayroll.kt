package com.company.payroll.model

import lombok.AllArgsConstructor
import lombok.RequiredArgsConstructor
import java.math.BigDecimal
import java.time.LocalDate

@AllArgsConstructor
@RequiredArgsConstructor
data class StaffPayroll(
    var prId: Int,
    var basicPay: BigDecimal,
    var overtimePay: BigDecimal,
    var allowance: BigDecimal,
    var transport: BigDecimal,
    var otherDeduction: BigDecimal,
    var employeeEpf: BigDecimal,
    var employeeSocso: BigDecimal,
    var employeeEis: BigDecimal,
    var employerEpf: BigDecimal,
    var employerSocso: BigDecimal,
    var employerEis: BigDecimal,
    var mtdPcb: BigDecimal,
    var totalPay: BigDecimal,
    var payPeriod: String,
    var paymentDate: LocalDate,
    var staffId: Int
)
