package com.company.payroll.model

import lombok.AllArgsConstructor
import lombok.RequiredArgsConstructor
import java.time.LocalDate

@AllArgsConstructor
@RequiredArgsConstructor
data class Resignation(
    var resignId: String,
    var resignDate: LocalDate,
    var resignStatus: UByte,
    var fileName: String,
    var fileSize: Long,
    var filePath: String,
    var staffId: Int
)
