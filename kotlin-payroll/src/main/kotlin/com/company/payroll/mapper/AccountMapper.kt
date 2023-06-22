package com.company.payroll.mapper

import com.company.payroll.model.Account
import org.springframework.stereotype.Repository

@Repository
interface AccountMapper {
  fun deleteByPrimaryKey(aId: Int): Int
  fun insertSelective(row: Account): Int
  fun selectList(): List<Account>
  fun selectByPrimaryKey(aId: Int): Account?
  fun selectByUsername(username: String): Account?
  fun updateByPrimaryKeySelective(row: Account): Int
  fun updateByPrimaryKey(row: Account): Int
  fun updateImagePathByUsername(row: Account): Int
}