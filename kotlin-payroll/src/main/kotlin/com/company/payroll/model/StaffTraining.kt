package com.company.payroll.model

import lombok.AllArgsConstructor
import lombok.RequiredArgsConstructor
import java.time.LocalDateTime

@AllArgsConstructor
@RequiredArgsConstructor
data class StaffTraining(
    var tId: Int,
    var trainingTitle: String,
    var description: String,
    var startDate: LocalDateTime,
    var endDate: LocalDateTime,
    var trainingStatus: UByte,
    var staffId: Int
)
