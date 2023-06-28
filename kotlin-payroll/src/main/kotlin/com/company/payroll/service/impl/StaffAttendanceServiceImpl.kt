package com.company.payroll.service.impl

import com.company.payroll.mapper.StaffAttendanceMapper
import com.company.payroll.model.StaffAttendance
import com.company.payroll.service.StaffAttendanceService
import com.github.pagehelper.PageHelper
import com.github.pagehelper.PageInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class StaffAttendanceServiceImpl(@Autowired private val staffAttendanceMapper: StaffAttendanceMapper): StaffAttendanceService {
  override fun getStaffAttendance(staffId: Int, startDate: LocalDate, endDate: LocalDate, page: Int, offset: Int): PageInfo<StaffAttendance> {
    PageHelper.startPage<StaffAttendance>(page, offset)
    return PageInfo<StaffAttendance>(staffAttendanceMapper.selectByStaffIdAndDate(staffId, startDate, endDate))
  }

  override fun staffCheckIn(staffAttendance: StaffAttendance): Int {
    return staffAttendanceMapper.insert(staffAttendance)
  }

  override fun staffUpdate(staffAttendance: StaffAttendance): Int {
    return staffAttendanceMapper.updateCheckTime(staffAttendance)
  }
}