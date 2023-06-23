package com.company.payroll.mapper

import com.company.payroll.model.StaffLeave
import org.springframework.stereotype.Repository

@Repository
interface LeaveMapper {
  fun deleteByPrimaryKey(lId: Int): Int
  fun insert(row: StaffLeave): Int
  fun insertSelective(row: StaffLeave): Int
  fun selectList(): List<StaffLeave>
  fun selectListByEId(staffId: Int): List<StaffLeave>?
  fun selectByPrimaryKey(lId: Int): StaffLeave?
  fun updateByPrimaryKeySelective(row: StaffLeave): Int
  fun updateByPrimaryKey(row: StaffLeave): Int
}