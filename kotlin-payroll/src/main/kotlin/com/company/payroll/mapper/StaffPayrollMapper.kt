package com.company.payroll.mapper

import com.company.payroll.model.StaffPayroll
import org.springframework.stereotype.Repository

@Repository
interface StaffPayrollMapper {
  fun deleteByPrimaryKey(prId: Int): Int
  fun insert(row: StaffPayroll): Int
  fun insertSelective(row: StaffPayroll): Int
  fun selectList(): List<StaffPayroll>?
  fun selectListByEId(eid: Int): List<StaffPayroll>?
  fun selectByPrimaryKey(prId: Int): StaffPayroll?
  fun updateByPrimaryKeySelective(row: StaffPayroll): Int
  fun updateByPrimaryKey(row: StaffPayroll): Int
}