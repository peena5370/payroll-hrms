package com.company.payroll.mapper

import com.company.payroll.model.Promotion
import org.springframework.stereotype.Repository

@Repository
interface PromotionMapper {
  fun deleteByPrimaryKey(pId: Int): Int
  fun insert(row: Promotion): Int
  fun insertSelective(row: Promotion): Int
  fun selectByEId(staffId: Int): List<Promotion>?
  fun selectByPrimaryKey(pId: Int): Promotion?
  fun selectList(): List<Promotion>?
  fun updateByPrimaryKey(row: Promotion): Int
  fun updateByPrimaryKeySelective(row: Promotion): Int
}