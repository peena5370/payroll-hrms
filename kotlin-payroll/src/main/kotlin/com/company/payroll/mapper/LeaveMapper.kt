package com.company.payroll.mapper

import com.company.payroll.model.Leave
import org.springframework.stereotype.Repository

@Repository
interface LeaveMapper {
  fun deleteByPrimaryKey(lId: Int): Int
  fun insert(row: Leave): Int
  fun insertSelective(row: Leave): Int
  fun selectList(): List<Leave>
  fun selectListByEId(staffId: Int): List<Leave>?
  fun selectByPrimaryKey(lId: Int): Leave?
  fun updateByPrimaryKeySelective(row: Leave): Int
  fun updateByPrimaryKey(row: Leave): Int
}