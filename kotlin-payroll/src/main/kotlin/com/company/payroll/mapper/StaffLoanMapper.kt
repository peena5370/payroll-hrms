package com.company.payroll.mapper

import com.company.payroll.model.StaffLoan
import org.springframework.stereotype.Repository

@Repository
interface StaffLoanMapper {
  fun deleteByPrimaryKey(loanId: Int): Int

  fun insert(row: StaffLoan): Int

  fun insertSelective(row: StaffLoan): Int

  fun selectByPrimaryKey(loanId: Int): StaffLoan

  fun selectList(): List<StaffLoan>

  fun selectListByStaffId(staffId: Int): List<StaffLoan>

  fun updateByPrimaryKeySelective(row: StaffLoan): Int

  fun updateByPrimaryKey(row: StaffLoan): Int
}