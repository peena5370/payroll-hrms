package com.company.payroll.mapper

import com.company.payroll.model.StaffLeave
import org.springframework.stereotype.Repository


@Repository
interface StaffLeaveMapper {
  fun deleteByPrimaryKey(lId: Int): Int

  fun insert(row: StaffLeave): Int

  fun insertSelective(row: StaffLeave): Int

  fun selectByPrimaryKey(lId: Int): StaffLeave

  fun selectList(): List<StaffLeave>

  fun selectListByStaffId(staffId: Int): List<StaffLeave>

  fun updateByPrimaryKeySelective(row: StaffLeave): Int

  fun updateByPrimaryKey(row: StaffLeave): Int
}