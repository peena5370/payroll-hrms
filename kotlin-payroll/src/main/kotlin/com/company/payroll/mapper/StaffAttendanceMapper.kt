package com.company.payroll.mapper

import com.company.payroll.model.StaffAttendance
import org.springframework.stereotype.Repository

@Repository
interface StaffAttendanceMapper {
  fun deleteByPrimaryKey(atId: Int): Int

  fun insert(row: StaffAttendance): Int

  fun insertSelective(row: StaffAttendance): Int

  fun selectByPrimaryKey(atId: Int): StaffAttendance?

  fun updateByPrimaryKeySelective(row: StaffAttendance): Int

  fun updateByPrimaryKey(row: StaffAttendance): Int
}