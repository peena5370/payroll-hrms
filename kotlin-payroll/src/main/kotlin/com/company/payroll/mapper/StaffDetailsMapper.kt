package com.company.payroll.mapper

import com.company.payroll.model.StaffDetails
import org.springframework.stereotype.Repository

@Repository
interface StaffDetailsMapper {
  fun countActiveDepartmentEmployee(deptNo: Int): Int
  fun deleteByPrimaryKey(staffId: Int): Int
  fun insert(row: StaffDetails): Int
  fun insertSelective(row: StaffDetails): Int
  fun selectByPrimaryKey(staffId: Int): StaffDetails?
  fun selectByResignId(resignId: Int): StaffDetails?
  fun selectList(): List<StaffDetails>
  fun updateByPrimaryKey(row: StaffDetails): Int
  fun updateByPrimaryKeySelective(row: StaffDetails): Int
}