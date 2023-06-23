package com.company.payroll.model

import lombok.AllArgsConstructor
import lombok.RequiredArgsConstructor
import java.time.LocalDate
import java.time.LocalDateTime

@AllArgsConstructor
@RequiredArgsConstructor
data class StaffLeave(
    var lId: Int,
    var refNum: String,
    var leaveType: String,
    var reason: String,
    var applicationDate: LocalDate,
    var dateFrom: LocalDateTime,
    var dateTo: LocalDateTime,
    var leaveStatus: UByte,
    var approvedDate: LocalDate,
    var staffId: Int
)
