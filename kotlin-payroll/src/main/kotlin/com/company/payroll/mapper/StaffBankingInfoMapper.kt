package com.company.payroll.mapper

import com.company.payroll.model.StaffBankingInfo
import org.springframework.stereotype.Repository

@Repository
interface StaffBankingInfoMapper {
  fun deleteByPrimaryKey(bId: Int): Int

  fun insert(row: StaffBankingInfo): Int

  fun insertSelective(row: StaffBankingInfo): Int

  fun selectByPrimaryKey(bId: Int): StaffBankingInfo

  fun updateByPrimaryKeySelective(row: StaffBankingInfo): Int

  fun updateByPrimaryKey(row: StaffBankingInfo): Int
}