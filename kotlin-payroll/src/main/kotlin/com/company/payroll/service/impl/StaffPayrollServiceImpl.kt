package com.company.payroll.service.impl

import com.company.payroll.model.StaffPayroll
import com.company.payroll.service.StaffPayrollService
import com.github.pagehelper.PageInfo
import org.springframework.stereotype.Service

@Service
class StaffPayrollServiceImpl: StaffPayrollService {
  override fun deleteStaffPayroll(prId: Int): Int {
    TODO("Not yet implemented")
  }

  override fun findStaffPayrollById(prId: Int): StaffPayroll? {
    TODO("Not yet implemented")
  }

  override fun findStaffPayrollByStaffId(staffId: Int): List<StaffPayroll>? {
    TODO("Not yet implemented")
  }

  override fun insertStaffPayroll(staffPayroll: StaffPayroll): StaffPayroll {
    TODO("Not yet implemented")
  }

  override fun listStaffPayroll(page: Int, offset: Int): PageInfo<StaffPayroll> {
    TODO("Not yet implemented")
  }

  override fun updateStaffPayroll(staffPayroll: StaffPayroll): StaffPayroll {
    TODO("Not yet implemented")
  }
}