package com.company.payroll.service.impl

import com.company.payroll.model.Leave
import com.company.payroll.model.Loan
import com.company.payroll.service.StaffApplicationService
import com.github.pagehelper.PageInfo
import org.springframework.stereotype.Service

@Service
class StaffApplicationServiceImpl: StaffApplicationService {
  override fun deleteLeave(lid: Int): Int {
    TODO("Not yet implemented")
  }

  override fun deleteLoan(lid: Int): Int {
    TODO("Not yet implemented")
  }

  override fun findLeaveByEId(eid: Int): List<Leave>? {
    TODO("Not yet implemented")
  }

  override fun findLeaveById(lid: Int): Leave? {
    TODO("Not yet implemented")
  }

  override fun findLoanByEId(eid: Int): List<Loan>? {
    TODO("Not yet implemented")
  }

  override fun findLoanById(lid: Int): Loan? {
    TODO("Not yet implemented")
  }

  override fun insertLeave(leave: Leave): Leave {
    TODO("Not yet implemented")
  }

  override fun insertLoan(loan: Loan): Loan {
    TODO("Not yet implemented")
  }

  override fun listLeave(page: Int, offset: Int): PageInfo<Leave> {
    TODO("Not yet implemented")
  }

  override fun listLoan(page: Int, offset: Int): PageInfo<Loan> {
    TODO("Not yet implemented")
  }

  override fun updateLeave(leave: Leave): Leave {
    TODO("Not yet implemented")
  }

  override fun updateLoan(loan: Loan): Loan {
    TODO("Not yet implemented")
  }
}