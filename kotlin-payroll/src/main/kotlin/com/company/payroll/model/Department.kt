package com.company.payroll.model

import lombok.AllArgsConstructor
import lombok.RequiredArgsConstructor

@AllArgsConstructor
@RequiredArgsConstructor
data class Department(
    var deptNo: Int,
    var deptName: String,
    var state: String,
    var country: String
)
