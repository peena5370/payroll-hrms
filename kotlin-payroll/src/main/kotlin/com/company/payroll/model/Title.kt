package com.company.payroll.model

import com.company.payroll.annotation.NoArg
import lombok.AllArgsConstructor
import lombok.RequiredArgsConstructor

@NoArg
data class Title(
    var titleNo: Int,
    var titleName: String = "",
    var titleDesc: String = ""
)
