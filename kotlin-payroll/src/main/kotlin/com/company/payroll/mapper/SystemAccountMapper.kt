package com.company.payroll.mapper

import com.company.payroll.model.SystemAccount
import org.springframework.stereotype.Repository

@Repository
interface SystemAccountMapper {
  fun deleteByPrimaryKey(aId: Int): Int
  fun insertSelective(row: SystemAccount): Int
  fun selectList(): List<SystemAccount>
  fun selectByPrimaryKey(aId: Int): SystemAccount?
  fun selectByUsername(username: String): SystemAccount?
  fun updateByPrimaryKeySelective(row: SystemAccount): Int
  fun updateByPrimaryKey(row: SystemAccount): Int
  fun updateImagePathByUsername(row: SystemAccount): Int
}