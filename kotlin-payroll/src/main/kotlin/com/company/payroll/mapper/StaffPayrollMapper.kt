package com.company.payroll.mapper

import com.company.payroll.model.StaffPayroll
import org.springframework.stereotype.Repository

@Repository
interface StaffPayrollMapper {
  fun deleteByPrimaryKey(prId: Int): Int

  fun insert(row: StaffPayroll): Int

  fun insertSelective(row: StaffPayroll): Int

  fun selectByPrimaryKey(prId: Int): StaffPayroll

  fun selectList(): List<StaffPayroll>

  fun selectListByStaffId(staffId: Int): List<StaffPayroll>

  fun updateByPrimaryKeySelective(row: StaffPayroll): Int

  fun updateByPrimaryKey(row: StaffPayroll): Int
}