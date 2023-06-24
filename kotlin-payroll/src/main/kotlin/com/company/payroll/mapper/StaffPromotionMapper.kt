package com.company.payroll.mapper

import com.company.payroll.model.StaffPromotion
import org.springframework.stereotype.Repository

@Repository
interface StaffPromotionMapper {
  fun deleteByPrimaryKey(pId: Int): Int

  fun insert(row: StaffPromotion): Int

  fun insertSelective(row: StaffPromotion): Int

  fun selectByPrimaryKey(pId: Int): StaffPromotion?

  fun updateByPrimaryKeySelective(row: StaffPromotion): Int

  fun updateByPrimaryKey(row: StaffPromotion): Int
}