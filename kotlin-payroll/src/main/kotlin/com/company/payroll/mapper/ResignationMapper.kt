package com.company.payroll.mapper

import com.company.payroll.model.Resignation
import org.springframework.stereotype.Repository

@Repository
interface ResignationMapper {
  fun deleteByPrimaryKey(resignId: Int): Int
  fun insert(row: Resignation): Int
  fun insertSelective(row: Resignation): Int
  fun selectList(): List<Resignation>?
  fun selectByPrimaryKey(resignId: Int): Resignation?
  fun updateByPrimaryKeySelective(row: Resignation): Int
  fun updateByPrimaryKey(row: Resignation): Int
}