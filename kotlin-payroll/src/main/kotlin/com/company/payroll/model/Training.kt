package com.company.payroll.model

import jdk.jfr.Description
import lombok.AllArgsConstructor
import lombok.RequiredArgsConstructor
import java.time.LocalDateTime

@AllArgsConstructor
@RequiredArgsConstructor
data class Training(
    var tId: Int,
    var startDate: LocalDateTime,
    var endDate: LocalDateTime,
    var trainingStatus: UByte,
    var description: String,
    var staffId: Int
)
