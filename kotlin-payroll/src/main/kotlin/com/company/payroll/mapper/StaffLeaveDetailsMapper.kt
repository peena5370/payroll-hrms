package com.company.payroll.mapper

import com.company.payroll.model.StaffLeaveDetails
import org.springframework.stereotype.Repository

@Repository
interface StaffLeaveDetailsMapper {
  fun deleteByPrimaryKey(ldId: Int): Int

  fun insert(row: StaffLeaveDetails): Int

  fun insertSelective(row: StaffLeaveDetails): Int

  fun selectByPrimaryKey(ldId: Int): StaffLeaveDetails

  fun updateByPrimaryKeySelective(row: StaffLeaveDetails): Int

  fun updateByPrimaryKey(row: StaffLeaveDetails): Int
}