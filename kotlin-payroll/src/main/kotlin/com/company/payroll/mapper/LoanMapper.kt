package com.company.payroll.mapper

import com.company.payroll.model.Loan
import org.springframework.stereotype.Repository

@Repository
interface LoanMapper {
  fun deleteByPrimaryKey(loanId: Int): Int
  fun insert(row: Loan): Int
  fun insertSelective(row: Loan): Int
  fun selectList(): List<Loan>?
  fun selectListByEId(staffId: Int): List<Loan>?
  fun selectByPrimaryKey(loanId: Int): Loan?
  fun updateByPrimaryKeySelective(row: Loan): Int
  fun updateByPrimaryKey(row: Loan): Int
}