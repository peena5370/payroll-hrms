package com.company.payroll.mapper

import com.company.payroll.model.Department
import org.springframework.stereotype.Repository

@Repository
interface DepartmentMapper {
  fun deleteByPrimaryKey(deptNo: Int): Int

  fun insert(row: Department): Int

  fun insertSelective(row: Department): Int

  fun selectByPrimaryKey(deptNo: Int): Department

  fun selectList(): List<Department>

  fun updateByPrimaryKeySelective(row: Department): Int

  fun updateByPrimaryKey(row: Department): Int
}