package com.company.payroll.model

import com.company.payroll.annotation.NoArg

@NoArg
data class StaffBankingInfo(
    var bId: Int,
    var bankName: String,
    var bankAccount: Long,
    var incomeTaxNo: String,
    var epfNo: Int
)
