package com.company.payroll.model

import lombok.AllArgsConstructor
import lombok.RequiredArgsConstructor

@AllArgsConstructor
@RequiredArgsConstructor
data class BankingInfo(
    var bId: Int,
    var bankName: String,
    var bankAccount: Long,
    var incomeTaxNo: String,
    var epfNo: Int
)
