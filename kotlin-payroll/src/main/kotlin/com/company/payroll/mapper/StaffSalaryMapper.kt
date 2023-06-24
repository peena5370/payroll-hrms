package com.company.payroll.mapper

import com.company.payroll.model.StaffSalary
import org.springframework.stereotype.Repository

@Repository
interface StaffSalaryMapper {
  fun deleteByPrimaryKey(sId: Int): Int

  fun insert(row: StaffSalary): Int

  fun insertSelective(row: StaffSalary): Int

  fun selectByPrimaryKey(sId: Int): StaffSalary?

  fun updateByPrimaryKeySelective(row: StaffSalary): Int

  fun updateByPrimaryKey(row: StaffSalary): Int
}