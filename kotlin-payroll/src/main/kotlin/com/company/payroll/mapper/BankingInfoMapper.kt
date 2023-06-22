package com.company.payroll.mapper

import com.company.payroll.model.BankingInfo
import org.springframework.stereotype.Repository

@Repository
interface BankingInfoMapper {
  fun deleteByPrimaryKey(bId: Int): Int
  fun insert(row: BankingInfo): Int
  fun insertSelective(row: BankingInfo): Int
  fun selectList(): List<BankingInfo>
  fun selectByPrimaryKey(bId: Int): BankingInfo?
  fun updateByPrimaryKeySelective(row: BankingInfo): Int
  fun updateByPrimaryKey(row: BankingInfo): Int
}