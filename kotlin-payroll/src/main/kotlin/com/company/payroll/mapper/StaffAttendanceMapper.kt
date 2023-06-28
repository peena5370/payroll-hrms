package com.company.payroll.mapper

import com.company.payroll.model.StaffAttendance
import org.apache.ibatis.annotations.Param
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface StaffAttendanceMapper {
  fun deleteByPrimaryKey(atId: Int): Int

  fun insert(row: StaffAttendance): Int

  fun insertSelective(row: StaffAttendance): Int

  fun selectByPrimaryKey(atId: Int): StaffAttendance

  fun selectByStaffIdAndDate(@Param("staffId") staffId: Int,
                             @Param("startDate") startDate: LocalDate,
                             @Param("endDate") endDate: LocalDate): List<StaffAttendance>

  fun updateCheckTime(row: StaffAttendance): Int

  fun updateByPrimaryKeySelective(row: StaffAttendance): Int

  fun updateByPrimaryKey(row: StaffAttendance): Int
}