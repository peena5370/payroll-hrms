package com.company.payroll.model

import lombok.AllArgsConstructor
import lombok.RequiredArgsConstructor

@AllArgsConstructor
@RequiredArgsConstructor
data class Title(
    var titleNo: Int,
    var titleName: String,
    var titleDesc: String
)
