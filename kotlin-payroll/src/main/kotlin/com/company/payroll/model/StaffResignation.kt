package com.company.payroll.model

import com.company.payroll.annotation.NoArg
import lombok.AllArgsConstructor
import lombok.RequiredArgsConstructor
import java.time.LocalDate

@NoArg
data class StaffResignation(
    var resignId: String,
    var reason: String,
    var resignDate: LocalDate,
    var resignStatus: UByte,
    var fileName: String?,
    var fileSize: Long?,
    var filePath: String?,
    var staffId: Int,
    var staffDetails: StaffDetails?
)
