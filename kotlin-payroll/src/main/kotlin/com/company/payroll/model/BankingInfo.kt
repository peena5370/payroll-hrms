package com.company.payroll.model

import lombok.AllArgsConstructor
import lombok.RequiredArgsConstructor

@AllArgsConstructor
@RequiredArgsConstructor
data class BankingInfo(
    var bId: Int,
    var accountBank: Long,
    var incomeTaxNo: String,
    var epfNo: Int
)
