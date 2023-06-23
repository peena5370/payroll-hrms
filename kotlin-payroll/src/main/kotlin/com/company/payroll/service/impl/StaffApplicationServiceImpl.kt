package com.company.payroll.service.impl

import com.company.payroll.model.StaffLeave
import com.company.payroll.model.StaffLoan
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

  override fun findLeaveByEId(eid: Int): List<StaffLeave>? {
    TODO("Not yet implemented")
  }

  override fun findLeaveById(lid: Int): StaffLeave? {
    TODO("Not yet implemented")
  }

  override fun findLoanByEId(eid: Int): List<StaffLoan>? {
    TODO("Not yet implemented")
  }

  override fun findLoanById(lid: Int): StaffLoan? {
    TODO("Not yet implemented")
  }

  override fun insertLeave(staffLeave: StaffLeave): StaffLeave {
    TODO("Not yet implemented")
  }

  override fun insertLoan(staffLoan: StaffLoan): StaffLoan {
    TODO("Not yet implemented")
  }

  override fun listLeave(page: Int, offset: Int): PageInfo<StaffLeave> {
    TODO("Not yet implemented")
  }

  override fun listLoan(page: Int, offset: Int): PageInfo<StaffLoan> {
    TODO("Not yet implemented")
  }

  override fun updateLeave(staffLeave: StaffLeave): StaffLeave {
    TODO("Not yet implemented")
  }

  override fun updateLoan(staffLoan: StaffLoan): StaffLoan {
    TODO("Not yet implemented")
  }
}