package com.company.payroll.model

import java.time.LocalDate
import java.time.LocalDateTime

/**
 * @author caroline
 *
 * <p> Created at 24 June 2023
 * <p> Use as model for table staff_attendance
 */
data class StaffAttendance(
    var atId: Int,
    var attendanceDate: LocalDate,
    var checkInOne: LocalDateTime,
    var checkOutOne: LocalDateTime,
    var checkInTwo: LocalDateTime,
    var checkOutTwo: LocalDateTime,
    var checkInThree: LocalDateTime,
    var checkOutThree: LocalDateTime,
    var staffDetails: StaffDetails
)
