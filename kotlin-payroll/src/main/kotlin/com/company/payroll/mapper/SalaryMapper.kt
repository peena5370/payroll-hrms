package com.company.payroll.mapper

import com.company.payroll.model.Salary
import org.springframework.stereotype.Repository

@Repository
interface SalaryMapper {
  fun deleteByPrimaryKey(sId: Int): Int
  fun insert(row: Salary): Int
  fun insertSelective(row: Salary): Int
  fun selectList(): List<Salary>?
  fun selectByPrimaryKey(sId: Int): Salary?
  fun updateByPrimaryKeySelective(row: Salary): Int
  fun updateByPrimaryKey(row: Salary): Int
}