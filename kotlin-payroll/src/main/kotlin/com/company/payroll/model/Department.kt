package com.company.payroll.model

import com.company.payroll.annotation.NoArg

@NoArg
data class Department(
    var deptNo: Int,
    var deptName: String,
    var deptAddress: String,
    var postalCode: Int,
    var state: String,
    var country: String
)
