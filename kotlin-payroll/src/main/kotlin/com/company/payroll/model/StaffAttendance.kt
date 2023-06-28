package com.company.payroll.model

import com.company.payroll.annotation.NoArg
import java.time.LocalDate
import java.time.LocalDateTime

/**
 * @author caroline
 *
 * <p> Created at 24 June 2023
 * <p> Use as model for table staff_attendance
 */
@NoArg
data class StaffAttendance(
    var atId: Int,
    var attendanceDate: LocalDate?,
    var checkInOne: LocalDateTime?,
    var checkOutOne: LocalDateTime?,
    var checkInTwo: LocalDateTime?,
    var checkOutTwo: LocalDateTime?,
    var checkInThree: LocalDateTime?,
    var checkOutThree: LocalDateTime?,
    var staffId: Int,
    var startDate: LocalDate?,
    var endDate: LocalDate?
)
