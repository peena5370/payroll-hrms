package com.company.payroll.model

import com.company.payroll.annotation.NoArg
import java.time.LocalDateTime

@NoArg
data class StaffTraining(
    var tId: Int,
    var trainingTitle: String,
    var description: String,
    var startDate: LocalDateTime,
    var endDate: LocalDateTime,
    var trainingStatus: UByte,
    var staffId: Int,
    var staffDetails: StaffDetails?
)
