package com.company.payroll.service

import com.company.payroll.model.StaffAttendance
import com.github.pagehelper.PageInfo
import java.time.LocalDate

interface StaffAttendanceService {
  fun getStaffAttendance(staffId: Int, startDate: LocalDate, endDate: LocalDate, page: Int, offset: Int): PageInfo<StaffAttendance>

  fun staffCheckIn(staffAttendance: StaffAttendance): Int

  fun staffUpdate(staffAttendance: StaffAttendance): Int
}